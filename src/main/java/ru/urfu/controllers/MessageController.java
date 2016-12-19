package ru.urfu.controllers;

import org.springframework.data.util.Pair;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.urfu.entities.Message;
import ru.urfu.model.InMemoryMessageDao;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author aarkaev
 * @since 08.08.2016
 */
@RestController
public class MessageController {
    @Inject
    InMemoryMessageDao messagesStorage;

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    ModelAndView renderAllMessages() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView mav = new ModelAndView("show_messages");
        mav.addObject("messages",
                messagesStorage.findAll(username).stream()
                        .map(msg -> Pair.of(msg.getMessage(), msg.getId()))
                        .collect(Collectors.toList()));
        return mav;
    }

    @RequestMapping(value = "/add_message", method = RequestMethod.POST)
    RedirectView add_message(@ModelAttribute("msg") String str_msg) {
        if ("".compareTo(str_msg) != 0) {
            Message msg = new Message();
            msg.setMessage(str_msg);
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            msg.setAuthorName(username);
            messagesStorage.create(msg);
        }
        return new RedirectView("/messages");
    }

    @RequestMapping(value = "/add_message", method = RequestMethod.GET)
    ModelAndView show_adding() {
        return new ModelAndView("add_message");
    }


    @RequestMapping(value = "/delete_message/{id}", method = RequestMethod.POST)
    RedirectView delete_message(@PathVariable("id") int id) {
        messagesStorage.remove(id);
        return new RedirectView("/messages");
    }

    @RequestMapping(value = "/get_message/{id}", method = RequestMethod.GET)
    Message get_message(@PathVariable("id") int id) {
        return messagesStorage.find(id);
    }

    @RequestMapping(value = "/get_messages", method = RequestMethod.GET)
    List<Message> get_messages() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return messagesStorage.findAll(username).stream()
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/get_all_messages", method = RequestMethod.GET)
    List<Message> get_all_messages() {
        return messagesStorage.findAll();
    }
}