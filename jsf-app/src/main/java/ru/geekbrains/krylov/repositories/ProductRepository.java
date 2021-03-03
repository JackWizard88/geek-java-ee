package ru.geekbrains.krylov.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ru.geekbrains.krylov.entities.Product;
import ru.geekbrains.krylov.utils.FactoryClass;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository implements Repository<Product> {

//    @PersistenceContext(unitName = "ds")
//    private EntityManager em;

    private final static Logger logger = LogManager.getLogger(ProductRepository.class);

    @Override
    public List<Product> findAll() {
        List<Product> products;
        Session session = FactoryClass.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        products = session.createNamedQuery("findAllProducts", Product.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return products;
//        return em.createNamedQuery("findAll", Product.class).getResultList();
    }


    @Override
    public Product findById(Long id) {
        return FactoryClass.getSessionFactory().getCurrentSession().get(Product.class, id);
//        return em.find(Product.class, id);
    }

    @Override
    public void saveOrUpdate(Product product) {

        Session session = FactoryClass.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        if (product.getId() == null) {
            session.save(product);
//            em.persist(product);
        }
        session.merge(product);
        session.getTransaction().commit();
        session.close();
//        em.merge(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
//        em.createNamedQuery("deleteById").setParameter("id", id).executeUpdate();
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
