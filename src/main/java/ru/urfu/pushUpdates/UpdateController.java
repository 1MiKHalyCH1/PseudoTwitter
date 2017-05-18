package ru.urfu.pushUpdates;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class UpdateController {
    @MessageMapping("/push")
    @SendTo("/topic/push")
    public PushMessage pushMessage(String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new PushMessage("Hello, " + message + "!");
    }
}
