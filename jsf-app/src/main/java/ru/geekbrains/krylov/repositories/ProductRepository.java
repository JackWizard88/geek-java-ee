package ru.geekbrains.krylov.repositories;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.geekbrains.krylov.entities.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class ProductRepository implements Repository <Product> {

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();
    private final AtomicLong identity = new AtomicLong(0);
    private final static Logger logger = LogManager.getLogger(ProductRepository.class);

    @PostConstruct
    public void init() {
        this.saveOrUpdate(new Product(null, "Product  1","Description of product 1", 100.00f));
        this.saveOrUpdate(new Product(null, "Product  2","Description of product 2", 200.00f));
        this.saveOrUpdate(new Product(null, "Product  3","Description of product 3", 300.00f));
        logger.info("ProductRepository initialized.. added " + this.findAll().size() + " units");
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public Product findById(Long id) {
        return productMap.get(id);
    }

    @Override
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            Long id = identity.incrementAndGet();
            product.setId(id);
        }
        productMap.put(product.getId(), product);
    }

    @Override
    public Product deleteById(Long id) {
        return productMap.remove(id);
    }
}
