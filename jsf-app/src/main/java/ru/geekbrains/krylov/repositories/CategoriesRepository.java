package ru.geekbrains.krylov.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ru.geekbrains.krylov.entities.Category;
import ru.geekbrains.krylov.entities.Product;
import ru.geekbrains.krylov.utils.FactoryClass;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;


@Named
@ApplicationScoped
public class CategoriesRepository implements Repository<Category> {

    private final static Logger logger = LogManager.getLogger(CategoriesRepository.class);

    @Override
    public List<Category> findAll() {
        List<Category> categories;
        Session session = FactoryClass.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        categories = session.createNamedQuery("findAllCategories", Category.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return categories;
    }

    @Override
    public Category findById(Long id) {
        return FactoryClass.getSessionFactory().getCurrentSession().get(Category.class, id);
    }

    @Override
    public void saveOrUpdate(Category category) {
        Session session = FactoryClass.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            if (category.getId() == null) {
                session.save(category);
            }
            session.update(category);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
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
