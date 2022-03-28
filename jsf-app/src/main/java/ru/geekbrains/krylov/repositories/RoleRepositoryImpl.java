package ru.geekbrains.krylov.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ru.geekbrains.krylov.entities.Role;
import ru.geekbrains.krylov.utils.FactoryClass;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class RoleRepositoryImpl {

    private final static Logger logger = LogManager.getLogger(RoleRepositoryImpl.class);

    public List<Role> findAll() {
        List<Role> roles;
        Session session = FactoryClass.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        roles = session.createQuery("from Role ", Role.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return roles;
    }

    public Role findById(Long id) {
        Session session = FactoryClass.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Role role = session.get(Role.class, id);
        session.close();
        return role;
    }

    public void saveOrUpdate(Role role) {

        Session session = FactoryClass.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        if (role.getId() == null) {
            session.save(role);
        }
        session.merge(role);
        session.getTransaction().commit();
        session.close();
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
