package ru.geekbrains.krylov.repositories;

import ru.geekbrains.krylov.entities.Product;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import java.util.List;

@Local
public interface ProductRepository {

    List<Product> findAll();

    Product findById(Long id);

    Product findByTitle(String productTitle);

    void saveOrUpdate(Product product);

    void deleteById(Long id);

}
