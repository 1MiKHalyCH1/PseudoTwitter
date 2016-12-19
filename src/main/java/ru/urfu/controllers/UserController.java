package ru.urfu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.urfu.entities.User;
import ru.urfu.model.UserDao;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by mikhail on 12/11/16.
 **/
@Controller
public class UserController {
    @Inject
    UserDao user_storage;

    @GetMapping("/login")
    String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    String register() {
        return "register";
    }

    @RequestMapping(name = "/register", method = RequestMethod.POST)
    String register(@ModelAttribute("username") String username,
                    @ModelAttribute("password") String password,
                    final RedirectAttributes redirectAttributes) {
        User user = new User();
        user.setLogin(username);
        user.setPassword(password);

        Optional<User> userWithLoginInDB = user_storage.findByLogin(username);
        if (!userWithLoginInDB.isPresent()) {
            user_storage.create(user);
            return "redirect:/";
        }
        redirectAttributes.addAttribute("error", "Error! Try again!");
        return "redirect:/register";
    }
}
