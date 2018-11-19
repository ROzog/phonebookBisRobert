package pl.robertozog.phonebookBis.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.robertozog.phonebookBis.models.entities.ContactEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends CrudRepository<ContactEntity,Integer > {
// logika obslugi encji
    @Query(nativeQuery = true, value = "SELECT * FROM contact") //mozliwosc uzywania jezyka sql
    List<ContactEntity> findAllContacts();

    @Query(nativeQuery = true, value = "SELECT * FROM contact WHERE LOWER(surname) = LOWER(?1) ")
    Optional<ContactEntity> findContactBySurname (String surname);

    List<ContactEntity> findAllByUser_Id(int id);

    boolean existsBySurname(String surname);
}
