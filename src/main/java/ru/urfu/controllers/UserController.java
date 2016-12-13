package ru.urfu.controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.urfu.model.User;
import ru.urfu.modelaaa.UserDao;

import javax.inject.Inject;

/**
 * Created by mikhail on 12/11/16.
 **/
@RestController
public class UserController {
    @Inject
    UserDao user_storage;

    @GetMapping("/login")
    ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping("/register")
    ModelAndView register() {
        return new ModelAndView("register");
    }

    @RequestMapping(name = "/register", method = RequestMethod.POST)
    RedirectView register(@ModelAttribute("username") String username,
                    @ModelAttribute("password") String password) {
        User user = new User();
        user.setLogin(username);
        user.setPassword(password);

        System.out.println(username + ' ' + password);

        user_storage.create(user);
        return new RedirectView("/");
    }
}
