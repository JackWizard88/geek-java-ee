package ru.geekbrains.krylov.services;

import ru.geekbrains.krylov.dto.ProductDTO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {

    List<ProductDTO> findAll();

    ProductDTO findById(Long id);

    void saveOrUpdate(ProductDTO productDTO);

    void deleteById(Long id);
}
