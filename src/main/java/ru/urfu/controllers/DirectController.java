package ru.urfu.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.urfu.entities.DirectMessage;
import ru.urfu.model.InMemoryDirectMessageDao;
import ru.urfu.model.InMemoryMessageDao;
import ru.urfu.model.UserDao;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
public class DirectController {
    @Inject InMemoryDirectMessageDao dmStorage;

    @RequestMapping(value = "/direct", method = RequestMethod.GET)
    ModelAndView renderAllMessages(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView mav = new ModelAndView("direct");
        mav.addObject("messages", dmStorage.findAll(username));
        return mav;
    }

    @RequestMapping(value = "/send_dm", method = RequestMethod.POST)
    RedirectView sendDirectMessage(@ModelAttribute("msg") String str_msg,
                                   @ModelAttribute("sender") String sender,
                                   @ModelAttribute("client") String client,
                                   HttpServletRequest request){
        DirectMessage msg = new DirectMessage();
        msg.setMessage(str_msg);
        msg.setSenderName(sender);
        msg.setClientName(client);
        dmStorage.create(msg);
        return new RedirectView(request.getHeader("Referer"));
    }

    @RequestMapping(value = "delete_dm/{id}", method = RequestMethod.POST)
    RedirectView removeDirectMessage(@PathVariable("id") int id, HttpServletRequest request){
        dmStorage.remove(id);
        return new RedirectView(request.getHeader("Referer"));
    }
}
