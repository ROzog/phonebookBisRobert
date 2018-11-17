package pl.robertozog.phonebookBis.models.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ContactForm {
    @Size(min = 2, max = 20)
    private String name;
    @Size(min = 2, max = 30)
    private String surname;
    @Pattern(regexp ="[0-9]{9,15}")
    private String telNumber;
}
