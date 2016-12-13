package ru.urfu;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.urfu.model.User;
import ru.urfu.modelaaa.UserDao;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

/**
 * Created by mikhail on 12/1/16.
 **/
@Named
public class UserDetailsServiceImpl implements UserDetailsService {
    @Inject
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userDao.find(s);
        return user.get().returnUser();
    }
}
