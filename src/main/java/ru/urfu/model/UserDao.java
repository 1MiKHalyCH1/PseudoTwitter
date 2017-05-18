package ru.urfu.model;
import ru.urfu.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> find(int id);
    Optional<User> findByLogin(String login);
    void create(User user);
    List<User> findAll();
    void update (User user);
}
