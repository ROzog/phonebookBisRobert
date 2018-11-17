package pl.robertozog.phonebookBis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.robertozog.phonebookBis.models.UserSession;
import pl.robertozog.phonebookBis.models.forms.ContactForm;
import pl.robertozog.phonebookBis.models.services.ContactService;

import javax.validation.Valid;

@Controller
public class PhoneBookController {
    final ContactService contactService;
    final UserSession userSession;

    @Autowired
    public PhoneBookController(ContactService contactService, UserSession userSession) {
        this.contactService = contactService;
        this.userSession = userSession;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/contact/add")
    public String showAddContactForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "addContact";
    }

    @PostMapping("/contact/add")
    public String getDataFromAddForm(@ModelAttribute @Valid ContactForm contactForm,
                                     BindingResult bindingResult,
                                     Model model) {
        if (bindingResult.hasErrors()){
           model.addAttribute("contactError", "Wrong data");
            return "addContact";
        }
        if(contactService.checkIfContactExist(contactForm.getSurname())){
            model.addAttribute("isSurnameBusy", true);
            return "addContact";
        }
        contactService.addContact(contactForm);
        return "addContact";
    }

    @GetMapping("/contact/show")
    public String showAllContacts(Model model) {
       if(!userSession.isLogin()){
           return "redirect:/user/login";
       }
       model.addAttribute("contacts", contactService.getContactsForLoginUser());
       return "contactList";
    }

    @GetMapping("/contact/show/id/{id}")
    @ResponseBody
    public String showOneContact(@PathVariable("id") int contactId) {
        return contactService
                .findOneContact(contactId)
                .map(s -> s.toString())
                .orElse("Contact with this id ont exist");
    }

    @GetMapping("contact/show/surname/{surname}")
    @ResponseBody
    public String showOneContactBySurname(@PathVariable("surname") String surname) {
        return contactService
                .findOneContact(surname)
                .map(s -> s.toString())
                .orElse("Contact with this surname not exist");
    }

    @GetMapping("/contact/delete/{contactId}")
    public String deleteContact(@PathVariable("contactId")int id){
        contactService.deleteContact(id);

        return "redirect:/contact/show";
    }
}
