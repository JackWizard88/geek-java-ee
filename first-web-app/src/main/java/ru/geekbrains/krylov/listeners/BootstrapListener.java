package ru.geekbrains.krylov.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.geekbrains.krylov.entities.Category;
import ru.geekbrains.krylov.entities.Product;
import ru.geekbrains.krylov.entities.User;
import ru.geekbrains.krylov.repositories.CategoriesRepository;
import ru.geekbrains.krylov.repositories.ProductRepository;
import ru.geekbrains.krylov.repositories.UserRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {

    private final static Logger logger = LogManager.getLogger(BootstrapListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        CategoriesRepository categoriesRepository = new CategoriesRepository();
        categoriesRepository.saveOrUpdate(new Category(null, "Category 1", "Description of category 1"));
        categoriesRepository.saveOrUpdate(new Category(null, "Category 2", "Description of category 2"));
        categoriesRepository.saveOrUpdate(new Category(null, "Category 3", "Description of category 3"));
        sce.getServletContext().setAttribute("categoryRepository", categoriesRepository);
        logger.info("CategoryRepository initialized.. added " + categoriesRepository.findAll().size() + " units");

        ProductRepository productRepository = new ProductRepository();
        productRepository.saveOrUpdate(new Product(null, "Product  1","Description of product 1", 100.00f));
        productRepository.saveOrUpdate(new Product(null, "Product  2","Description of product 2", 200.00f));
        productRepository.saveOrUpdate(new Product(null, "Product  3","Description of product 3", 300.00f));
        sce.getServletContext().setAttribute("productRepository", productRepository);
        logger.info("ProductRepository initialized.. added " + productRepository.findAll().size() + " units");

        UserRepository userRepository = new UserRepository();
        userRepository.saveOrUpdate(new User(null, "Ivan", "Ivanov"));
        userRepository.saveOrUpdate(new User(null, "Petr", "Petrov"));
        userRepository.saveOrUpdate(new User(null, "Sidor", "Sidorov"));
        sce.getServletContext().setAttribute("userRepository", userRepository);
        logger.info("UserRepository initialized.. added " + userRepository.findAll().size() + " units");

    }
}
