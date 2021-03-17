package ru.geekbrains.krylov.repositories;

import ru.geekbrains.krylov.entities.Product;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import java.util.List;

@Local
public interface ProductRepository {

    List<Product> findAll();

    @TransactionAttribute
    Product findById(Long id);

    void saveOrUpdate(Product product);

    void deleteById(Long id);

}
