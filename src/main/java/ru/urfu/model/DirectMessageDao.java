package ru.urfu.model;

import ru.urfu.entities.DirectMessage;

import java.util.List;

public interface DirectMessageDao {
    void create(DirectMessage msg);
    DirectMessage find(int id);
    List<DirectMessage> findAll(String username);
    List<DirectMessage> findAll();
    void remove(int id);
    void update (DirectMessage msg);
}
