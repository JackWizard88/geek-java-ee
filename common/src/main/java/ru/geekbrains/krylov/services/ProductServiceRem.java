package ru.geekbrains.krylov.services;

import ru.geekbrains.krylov.dto.ProductDTO;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ProductServiceRem {

    List<ProductDTO> findAll();

    ProductDTO findById(Long id);

    void saveOrUpdate(ProductDTO productDTO);

    void deleteById(Long id);
}
