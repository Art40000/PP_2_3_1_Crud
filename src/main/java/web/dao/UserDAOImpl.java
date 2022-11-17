package web.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import web.model.User;
import javax.persistence.*;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Override
    public void add(User user) {
        if(this.getUser(user) == null){
            this.emf.createEntityManager().unwrap(Session.class).save(user);
        }
    }
    @Override
    public void remove(int id) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, id);
        if(user!=null){
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }
    }

    @Override
    public User getUserById(int id) {
       return emf.createEntityManager().find(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return this.emf.createEntityManager().unwrap(Session.class).createQuery("from User").getResultList();
    }

    @Override
    public User getUser(User user) {
        Session session = emf.createEntityManager().unwrap(Session.class);
        User res = null;
        String hql = "from User where name = :nm and lastname = :ln and age = :ag";
        TypedQuery<User> query=session.createQuery(hql).
                setParameter("nm", user.getName()).setParameter("ln", user.getLastname()).
                    setParameter("ag", user.getAge());
        try {
            res = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return res;
    }
}
