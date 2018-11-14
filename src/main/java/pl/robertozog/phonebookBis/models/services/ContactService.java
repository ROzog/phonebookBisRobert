package pl.robertozog.phonebookBis.models.services;

import org.springframework.stereotype.Service;
import pl.robertozog.phonebookBis.models.ContactForm;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {
    private List<ContactForm> contactForms = new ArrayList<>();

    public void addContact(ContactForm contactForm) {
        contactForms.add(contactForm);
    }

    public List<ContactForm> getContactForms() {
        return contactForms;
    }
}
