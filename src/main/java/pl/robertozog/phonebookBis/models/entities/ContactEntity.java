package pl.robertozog.phonebookBis.models.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@Data
public class ContactEntity {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String surname;

    @Column(name = "phone")
    private String number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @Override
    public String toString() {
        return "ContactEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
