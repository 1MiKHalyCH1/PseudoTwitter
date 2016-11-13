package ru.urfu;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author aarkaev
 * @since 08.08.2016
 */
@RestController
public class MessageStorage {
    private static final List<Message> _messages = new ArrayList<>();

//    static {
//        _messages.add(new Message("first message"));
//        _messages.add(new Message("second message"));
//    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    ModelAndView renderAllMessages() {
        ModelAndView mav = new ModelAndView("show_messages");
        List<String> messages;
        if (_messages.size() == 0)
            messages = Arrays.asList();
        else
         messages = _messages
                .stream()
                .map(msg ->msg.get_message())
                 .collect(Collectors.toList());
        mav.addObject("messages", messages);
            return mav;
    }

    @RequestMapping(value = "/add_message", method = RequestMethod.POST)
    RedirectView add_message(@ModelAttribute("msg") String msg) {
        if ("".compareTo(msg) != 0) {
            _messages.add(new Message(msg));
        }
        return new RedirectView("/messages");
    }

    @RequestMapping(value = "/add_message", method = RequestMethod.GET)
    ModelAndView show_adding() {
        return new ModelAndView("add_message");
    }


    @RequestMapping(value = "/delete_message/{id}", method = RequestMethod.POST)
    RedirectView delete_message(@PathVariable("id") int id) {
        _messages.remove(id);
        return new RedirectView("/messages");
    }
}