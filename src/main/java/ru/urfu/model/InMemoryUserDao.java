package ru.urfu.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.urfu.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Primary
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

    public Optional<User> findByLogin(String login) {
        List<User> res = em.createQuery
                ("from " + User.class.getName() + " user where user.login = :login", User.class)
                .setParameter("login", login)
                .getResultList();
        return res.size() > 0 ? Optional.ofNullable(res.get(0)) : Optional.empty();
    }
}
