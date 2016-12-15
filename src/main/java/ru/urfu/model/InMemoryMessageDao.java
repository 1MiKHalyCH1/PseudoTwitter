package ru.urfu.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.urfu.entities.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Primary
@Repository
public class InMemoryMessageDao implements MessageDao {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void create(Message msg) {
        em.persist(msg);
    }

    @Override
    public Message find(int id) {
        return em.find(Message.class, id);
    }

    @Override
    public List<Message> findAll(String username) {
        return em.createQuery("from " + Message.class.getName() + " msg" +
                " where msg.authorName=:username", Message.class)
                .setParameter("username", username)
                .getResultList();
    }

    @Transactional
    @Override
    public void remove(int id) {
        Message msg = find(id);
        em.remove(msg);
    }
}
