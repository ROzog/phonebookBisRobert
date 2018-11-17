package pl.robertozog.phonebookBis.models.forms;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterForm {
    @Size(min = 3, max=15)                  //jako przyklad po ponizej ta formula jest zwalidowana
    @Pattern(regexp = "[a-zA-Z0-9]{3,20}")  //validacja hasla
    private String login;
    @Size(min = 5, max = 30)
    private String password;
}
