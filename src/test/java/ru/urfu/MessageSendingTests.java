package ru.urfu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageSendingTests {

    @Test
    public void sendMessageBase() {
        String body = MessageStorage.sendNewMessage("abc");
        assertThat(body).contains("Message added!");
    }

    @Test
    public void sendMessageEmpty() {
        String body = MessageStorage.sendNewMessage("");
        assertThat(body).contains("Twitt anything!");
    }

    @Test
    public void sendMessageError() {
        String body = MessageStorage.sendNewMessage(null);
        assertThat(body).contains("Error");
    }
}
