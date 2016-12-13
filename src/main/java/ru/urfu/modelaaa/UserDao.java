package ru.urfu.modelaaa;

import ru.urfu.model.User;

import java.util.Optional;

/**
 * Created by mikhail on 12/1/16.
 **/
public interface UserDao {
    Optional<User> find(int id);
    Optional<User> find(String login);
    void create(User user);
}
