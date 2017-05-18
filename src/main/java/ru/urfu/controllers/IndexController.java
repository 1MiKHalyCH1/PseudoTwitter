package ru.urfu.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.urfu.entities.User;
import ru.urfu.model.InMemoryUserDao;

import javax.inject.Inject;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class IndexController {
    @Inject
    InMemoryUserDao userStorage;

    @RequestMapping("/")
    public String index(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> currentUser = userStorage.findByLogin(username);
        if (currentUser.isPresent()) {
            User user = currentUser.get();
            model.addAttribute("user", user);
        }
        else {
        }
        return "index";
    }
}
