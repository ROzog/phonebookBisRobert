package pl.robertozog.phonebookBis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.robertozog.phonebookBis.models.ContactForm;
import pl.robertozog.phonebookBis.models.services.ContactService;

@Controller
public class PhoneBookController {
    final ContactService contactService;

    @Autowired
    public PhoneBookController(ContactService contactService) {
        this.contactService = contactService;
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
    public String getDataFromAddForm(@ModelAttribute ContactForm contactForm){
       contactService.addContact(contactForm);
        return "addContact"; //todo change after save data
    }

    @GetMapping("/contact/show")
    @ResponseBody
    public String showAllContacts(){
        return contactService.getContactForms().toString();
    }
}
