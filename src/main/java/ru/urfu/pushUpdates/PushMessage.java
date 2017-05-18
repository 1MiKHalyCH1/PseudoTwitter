package ru.urfu.pushUpdates;

public class PushMessage {

    private String content;

    public PushMessage() {
    }

    public PushMessage(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}