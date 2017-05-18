package ru.urfu.model;

import ru.urfu.entities.Message;
import java.util.List;

public interface MessageDao {
    void create(Message msg);
    Message find(int id);
    List<Message> findAll(String username);
    List<Message> findAll();
    void remove(int id);
    void update(Message msg);
}
