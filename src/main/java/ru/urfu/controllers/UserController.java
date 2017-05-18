package ru.urfu.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.urfu.entities.User;
import ru.urfu.model.InMemoryMessageDao;
import ru.urfu.model.InMemoryUserDao;
import ru.urfu.model.UserDao;

import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by mikhail on 12/11/16.
 **/
@Controller
public class UserController {
    @Inject InMemoryUserDao userStorage;
    @Inject InMemoryMessageDao messageStorage;

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

        Optional<User> userWithLoginInDB = userStorage.findByLogin(username);
        if (!userWithLoginInDB.isPresent()) {
            userStorage.create(user);
            return "redirect:/";
        }
        redirectAttributes.addAttribute("error", "Error! Try again!");
        return "redirect:/register";
    }

    @RequestMapping(value="/{username}", method = RequestMethod.GET)
    ModelAndView profilePage(@PathVariable("username") String username){
        User currentUser = userStorage.findByLogin(username).get();
        ModelAndView mav = new ModelAndView("profile");
        mav.addObject("user", currentUser);
        mav.addObject("messages", messageStorage.findAll(username));
        return mav;
    }
}
