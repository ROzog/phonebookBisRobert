package pl.robertozog.phonebookBis.models;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import pl.robertozog.phonebookBis.models.entities.UserEntity;

@Component //@Service == @Component == @Controller == @Repository
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)  //@Scope tutaj zmieniamy z sigleton na session zasieg sesyjny powoduje ze spring robi klucz sesyjny
@Data
public class UserSession {
    private boolean isLogin;
    private UserEntity userEntity;
}
