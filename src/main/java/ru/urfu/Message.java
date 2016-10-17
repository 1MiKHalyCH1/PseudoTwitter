package ru.urfu;

/**
 * Created by Михаил on 12.10.2016.
 */

public class Message {
    private String _message;
    private static final int MAX_LENGTH = 140;

    public String get_message() {
        return _message;
    }

    public Message(String message) {
        if (message.length() > MAX_LENGTH)
            _message = message.substring(0, MAX_LENGTH);
        else
            _message = message;
    }
}
