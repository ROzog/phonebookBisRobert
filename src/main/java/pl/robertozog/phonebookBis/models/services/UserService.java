package pl.robertozog.phonebookBis.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.robertozog.phonebookBis.models.UserSession;
import pl.robertozog.phonebookBis.models.forms.LoginForm;
import pl.robertozog.phonebookBis.models.entities.UserEntity;
import pl.robertozog.phonebookBis.models.forms.RegisterForm;
import pl.robertozog.phonebookBis.models.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    final UserRepository userRepository;
    final UserSession userSession;
    final PasswordHashingService passwordHashingService;

    @Autowired
    public UserService(UserRepository userRepository, UserSession userSession, PasswordHashingService passwordHashingService) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.passwordHashingService = passwordHashingService;
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public void registerUser(RegisterForm registerForm) {
        UserEntity newUser = new UserEntity();
        newUser.setLogin(registerForm.getLogin());
        newUser.setPassword(passwordHashingService.hash(registerForm.getPassword()));
        userRepository.save(newUser);

    }

    public boolean tryLogin(LoginForm loginForm) {
        Optional<UserEntity> userOptional =
                userRepository.getUserByLogin(loginForm.getLogin());
        if (userOptional.isPresent()) {
            if (passwordHashingService.matches(loginForm.getPassword(),
                    userOptional.get().getPassword())) {

                userSession.setLogin(true);
                userSession.setUserEntity(userOptional.get());
            }
        }
        return userSession.isLogin();
    }
}
