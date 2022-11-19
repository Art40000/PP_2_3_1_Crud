package web.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.*;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(User user) {
        if(!doesThisUserExist(user)){
            em.unwrap(Session.class).save(user);
        }
    }
    @Override
    public void remove(int id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }

    @Override
    public void update(User fromUser, User toUser) {
        if(!doesThisUserExist(fromUser)) {
            toUser.setName(fromUser.getName());
            toUser.setLastname(fromUser.getLastname());
            toUser.setAge((fromUser.getAge()));
            em.merge(toUser);
        }
    }

    @Override
    public User getUserById(int id) {
       return em.find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return em.unwrap(Session.class).createQuery("from User").getResultList();
    }

    @Override
    public boolean doesThisUserExist(User user) {
        Session session = em.unwrap(Session.class);
        String hql = "from User where name = :nm and lastname = :ln and age = :ag";
        TypedQuery<User> query=session.createQuery(hql).
                setParameter("nm", user.getName()).setParameter("ln", user.getLastname()).
                    setParameter("ag", user.getAge());
        try {
            query.getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }
}
