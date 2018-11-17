package pl.robertozog.phonebookBis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.robertozog.phonebookBis.models.forms.LoginForm;
import pl.robertozog.phonebookBis.models.forms.RegisterForm;
import pl.robertozog.phonebookBis.models.services.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/register")
    public String showRegistration(Model model){
        model.addAttribute("registerForm", new RegisterForm());
        return "registrationTemplate";
    }

    @PostMapping("user/register")
    public String registerUser(@ModelAttribute @Valid RegisterForm registerForm,
                               BindingResult bindingResult,
                               Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("registerInfo", "Wrong login or password"); //jedna z opcji wyslania komunikatu
            return "registrationTemplate";
        }
        if(userService.existsByLogin(registerForm.getLogin())){
            model.addAttribute("registerInfo", "Login is Busy");
            return "registrationTemplate";
        }
        userService.registerUser(registerForm);
        model.addAttribute("registerInfo", "User registered");
        return "redirect:/contact/add";
    }

    @GetMapping("/user/login")
    public String loginUser(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "loginTemplate";
    }

    @PostMapping("/user/login")
    public String loggedUser(@ModelAttribute LoginForm loginForm, Model model){
        if (userService.tryLogin(loginForm)){
            return "redirect:/contact/add";
        }
        model.addAttribute("loginErrorInfo", "Login or Password Error");
        return "loginTemplate";
    }
}
