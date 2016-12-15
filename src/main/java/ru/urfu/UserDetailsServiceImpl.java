package ru.urfu;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.urfu.entities.User;
import ru.urfu.model.UserDao;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
public class UserDetailsServiceImpl implements UserDetailsService {
    @Inject UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByLogin(s);
        return user.get().returnUser();
    }
}
