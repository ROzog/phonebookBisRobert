package pl.robertozog.phonebookBis.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="users")
public class UserEntity {
    @Id
    @GeneratedValue
    private int id;
    private String login;
    private String password;


    @OneToMany(mappedBy = "user",orphanRemoval = true, cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<ContactEntity> contacts;

}
