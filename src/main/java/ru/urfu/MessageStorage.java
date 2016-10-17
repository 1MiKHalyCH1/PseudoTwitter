package ru.urfu;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author aarkaev
 * @since 08.08.2016
 */
@RestController
public class MessageStorage {
    private static final List<Message> _messages = new ArrayList<>();

    public static List<Message> get_messages() {
        return new ArrayList<>(_messages);
    }

    static {
        _messages.add(new Message("Моё первое сообщение"));
        _messages.add(new Message("Здесь будет новое сообщение"));
    }

    @RequestMapping("/messages")
    String renderAllMessages() {
        String messages = _messages
            .stream()
            .map(msg -> "<li>" + msg.get_message() + "</li>")
            .collect(Collectors.joining());

        return
            "<html>" +
            "   <link rel=\"stylesheet\" type=\"text/css\" href=\"/twitter.css\"/>" +
            "   <body>" +
            "       <h1>twitter</h1>" +
            "       This is your twitter application" +
            "       <ul class=\"messages\">" +
                        messages +
            "       </ul>"+
            "       <a href=\"/\">main</a>" +
            "   </body>" +
            "</html>";
    }

    @RequestMapping("/sending")
    static String sendNewMessage(@RequestParam(value="msg", required=false) String msg) {
        if (msg == null)
            return
                "<html>" +
                "   Error <br>"+
                "   <a href=\"/messages\">messages</a>" +
                "</html>";
        if (msg == "")
            return
                "<html>" +
                "   Twitt anything! <br>"+
                "   <a href=\"/\">main</a>" +
                "</html>";
        _messages.add(new Message(msg));
        return
                String.format("<html>" +
                        "   <link rel=\"stylesheet\" type=\"text/css\" href=\"/twitter.css\"/>" +
                        "   <body>" +
                        "       Message added! %s%n<br>" +
                        "       <a href=\"/messages\">messages</a>" +
                        "   </body>" +
                        "</html>", (msg.length() > 140)?"(corrected to 140 characters)":"");
    }
}