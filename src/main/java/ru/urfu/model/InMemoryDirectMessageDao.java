package ru.urfu.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.urfu.entities.DirectMessage;
import ru.urfu.entities.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Primary
@Repository
public class InMemoryDirectMessageDao implements DirectMessageDao {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void create(DirectMessage msg) {
        em.persist(msg);
    }

    @Override
    public DirectMessage find(int id) {
        return em.find(DirectMessage.class, id);
    }

    @Override
    public List<DirectMessage> findAll(String username) {
        return em.createQuery("from " + DirectMessage.class.getName() + " msg" +
                " where msg.clientName=:username", DirectMessage.class)
                .setParameter("username", username)
                .getResultList();
    }

    @Override
    public List<DirectMessage> findAll() {
        return em.createQuery("from " + DirectMessage.class.getName(), DirectMessage.class)
                .getResultList();
    }

    @Transactional
    @Override
    public void remove(int id) {
        DirectMessage msg = find(id);
        em.remove(msg);
    }

    @Transactional
    @Override
    public void update (DirectMessage msg) {
        em.merge(msg);
    }
}
