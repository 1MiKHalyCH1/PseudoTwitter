package ru.urfu;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.urfu.entities.User;
import ru.urfu.model.UserDao;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Named
public class UserProvider implements AuthenticationProvider {
    @Inject
    UserDao storage;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<User> userFromDB = storage.findByLogin(login);
        if (userFromDB.isPresent()) {
            User user = userFromDB.get();
            if (user != null && user.getPassword().equals(password)) {
                List<GrantedAuthority> grantedAuths = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
                return new UsernamePasswordAuthenticationToken(login, password, grantedAuths);
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
