package ru.urfu.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.urfu.entities.User;
import ru.urfu.model.InMemoryMessageDao;
import ru.urfu.model.InMemoryUserDao;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class BoardController {
    @Inject InMemoryMessageDao messagesStorage;
    @Inject InMemoryUserDao userStorage;

    @RequestMapping(value = "/my_board")
    ModelAndView renderMyMessages() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView mav = new ModelAndView("show_messages");
        mav.addObject("messages", new ArrayList<>(messagesStorage.findAll(username)));
        mav.addObject("user", userStorage.findByLogin(username).get());
        return mav;
    }

    @RequestMapping(value = "/friends_board")
    ModelAndView renderFriendsMessages() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userStorage.findByLogin(username).get();
        Set<String> friends = currentUser.getFriends().stream().map(User::getLogin).collect(Collectors.toSet());
                ModelAndView mav = new ModelAndView("show_messages");
        mav.addObject("messages",
                messagesStorage.findAll()
                        .stream()
                        .filter(x -> x.getAuthorName().equals(username) || friends.contains(x.getAuthorName()))
                        .collect(Collectors.toList()));
        mav.addObject("user", currentUser);
        return mav;
    }

    @RequestMapping(value = "/general_board")
    ModelAndView renderGeneralMessages() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView mav = new ModelAndView("show_messages");
        mav.addObject("messages", new ArrayList<>(messagesStorage.findAll()));
        mav.addObject("user", userStorage.findByLogin(username).get());
        return mav;
    }
}
