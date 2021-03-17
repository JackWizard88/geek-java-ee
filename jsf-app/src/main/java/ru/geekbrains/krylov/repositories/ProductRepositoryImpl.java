package ru.geekbrains.krylov.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ru.geekbrains.krylov.entities.Product;
import ru.geekbrains.krylov.utils.FactoryClass;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ProductRepositoryImpl implements ProductRepository {

    private final static Logger logger = LogManager.getLogger(ProductRepositoryImpl.class);

    @Override
    public List<Product> findAll() {
        List<Product> products;
        Session session = FactoryClass.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        products = session.createNamedQuery("findAllProducts", Product.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return products;
    }


    @Override
    public Product findById(Long id) {
        Session session = FactoryClass.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }

    @Override
    public void saveOrUpdate(Product product) {

        Session session = FactoryClass.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        if (product.getId() == null) {
            session.save(product);
        }
        session.merge(product);
        session.getTransaction().commit();
        session.close();
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
