package ru.urfu.serializers;

import ru.urfu.entities.Message;

import java.util.List;

public interface ISerializer {
    String serialize(List<Message> messages);
}
