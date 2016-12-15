package ru.urfu.model;

import org.springframework.stereotype.Repository;
import ru.urfu.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by mikhail on 12/1/16.
 **/
@Repository
public class InMemoryUserDao implements UserDao {
    @PersistenceContext private EntityManager em;

    @Transactional
    public void create(User user) {
        em.persist(user);
    }

    public Optional<User> find(int id) {
        return Optional.ofNullable(em.find(User.class, id));
    }
    public Optional<User> find(String login) {
        return Optional.ofNullable(em.createQuery
                ("from " + User.class.getName() + " user where user.login = :login", User.class)
                .setParameter("login", login)
                .getSingleResult());
    }
}
