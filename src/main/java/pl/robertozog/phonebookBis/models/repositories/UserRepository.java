package pl.robertozog.phonebookBis.models.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.robertozog.phonebookBis.models.entities.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    boolean existsByLogin(String login);


    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE login =?1")
    Optional<UserEntity> getUserByLogin(String login);
}
