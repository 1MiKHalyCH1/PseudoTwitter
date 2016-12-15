package ru.urfu.model;

import ru.urfu.entities.Message;
import java.util.List;

/**
 * Created by mikhail on 12/14/16.
 **/
public interface MessageDao {
    void create(Message msg);
    Message find(int id);
    List<Message> findAll(String username);
    void remove(int id);
}
