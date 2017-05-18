package ru.urfu.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.urfu.entities.Message;
import ru.urfu.entities.User;
import ru.urfu.model.InMemoryMessageDao;
import ru.urfu.model.InMemoryUserDao;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {
    @Inject InMemoryMessageDao messagesStorage;
    @Inject InMemoryUserDao userStorage;

//    @RequestMapping(value = "/messages", method = RequestMethod.GET)
//    ModelAndView renderAllMessages() {
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        ModelAndView mav = new ModelAndView("show_messages");
//        mav.addObject("messages", new ArrayList<>(messagesStorage.findAll()));
//        mav.addObject("user", userStorage.findByLogin(username).get());
//        return mav;
//    }

    @RequestMapping(value = "/add_message", method = RequestMethod.POST)
    RedirectView add_message(@ModelAttribute("msg") String str_msg, HttpServletRequest request) {
        if ("".compareTo(str_msg) != 0) {
            Message msg = new Message();
            msg.setMessage(str_msg);
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            msg.setAuthorName(username);
            messagesStorage.create(msg);
        }
        return new RedirectView(request.getHeader("Referer"));
    }

    @RequestMapping(value = "/delete_message/{id}", method = RequestMethod.POST)
    RedirectView delete_message(@PathVariable("id") int id, HttpServletRequest request) {
        messagesStorage.remove(id);
        return new RedirectView(request.getHeader("Referer"));
    }

    @RequestMapping(value = "/get_message/{id}", method = RequestMethod.GET)
    Message get_message(@PathVariable("id") int id) {
        return messagesStorage.find(id);
    }

    @RequestMapping(value = "/get_messages", method = RequestMethod.GET)
    List<Message> get_messages() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ArrayList<>(messagesStorage.findAll(username));
    }

    @RequestMapping(value = "/get_all_messages", method = RequestMethod.GET)
    List<Message> get_all_messages() {
        return messagesStorage.findAll();
    }

    @RequestMapping(value = "/like_message/{id}", method = RequestMethod.POST)
    RedirectView like_message(@PathVariable("id") int id, HttpServletRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userStorage.findByLogin(username).get();
        Message msg = messagesStorage.find(id);
        msg.getLikes().add(user.getId());
        messagesStorage.update(msg);
        return new RedirectView(request.getHeader("Referer"));
    }

    @RequestMapping(value = "/dislike_message/{id}", method = RequestMethod.POST)
    RedirectView dislike_message(@PathVariable("id") int id, HttpServletRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userStorage.findByLogin(username).get();
        Message msg = messagesStorage.find(id);
        msg.getLikes().remove(user.getId());
        messagesStorage.update(msg);
        return new RedirectView(request.getHeader("Referer"));
    }

    @RequestMapping(value = "/retwit_message/{id}", method = RequestMethod.POST)
    RedirectView retwit_message(@PathVariable("id") int id, HttpServletRequest request){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userStorage.findByLogin(username).get();
        Message retwitedMsg = messagesStorage.find(id);
        Message msg = new Message();
        msg.setMessage(retwitedMsg.getMessage());
        msg.setAuthorName(user.getLogin());
        msg.setRetwitedAuthor(retwitedMsg.getAuthorName());
        messagesStorage.create(msg);
        return new RedirectView(request.getHeader("Referer"));
    }
}