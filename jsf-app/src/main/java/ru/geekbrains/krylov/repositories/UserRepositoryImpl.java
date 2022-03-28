package ru.geekbrains.krylov.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ru.geekbrains.krylov.entities.User;
import ru.geekbrains.krylov.utils.FactoryClass;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserRepositoryImpl {

    private final static Logger logger = LogManager.getLogger(UserRepositoryImpl.class);

    public List<User> findAll() {
        List<User> users;
        Session session = FactoryClass.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        users = session.createQuery("from User", User.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    public User findById(Long id) {
        Session session = FactoryClass.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    public User saveOrUpdate(User user) {

        Session session = FactoryClass.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        if (user.getId() == null) {
            session.save(user);
        }
        User saved = (User) session.merge(user);
        session.getTransaction().commit();
        session.close();
        return saved;

    }

    public void deleteById(Long id) {
        Session session = FactoryClass.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

}
