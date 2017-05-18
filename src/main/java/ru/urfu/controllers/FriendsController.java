package ru.urfu.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.urfu.entities.User;
import ru.urfu.model.InMemoryUserDao;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FriendsController {
    @Inject InMemoryUserDao userStorage;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    ModelAndView get_friends(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<User> users = userStorage.findAll();
        User currentUser = userStorage.findByLogin(username).get();

        ModelAndView mav = new ModelAndView("users");
        mav.addObject("users", users
            .stream()
            .filter(x -> x.getId() != currentUser.getId())
            .collect(Collectors.toList()));
        mav.addObject("user", currentUser);
        return mav;
    }

    @RequestMapping(value = "/add_friend/{id}", method = RequestMethod.POST)
    RedirectView add_friend(@PathVariable("id") int id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userStorage.findByLogin(username).get();
        User friend = userStorage.find(id).get();
        currentUser.getFriends().add(friend);
        userStorage.update(currentUser);
        userStorage.update(friend);
        return new RedirectView("/users");
    }

    @RequestMapping(value = "/remove_friend/{id}", method = RequestMethod.POST)
    RedirectView remove_friend(@PathVariable("id") int id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userStorage.findByLogin(username).get();
        User friend = userStorage.find(id).get();
        currentUser.getFriends().remove(friend);
        userStorage.update(currentUser);
        userStorage.update(friend);
        return new RedirectView("/users");
    }

}
