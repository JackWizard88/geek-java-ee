package ru.geekbrains.krylov.services;

import ru.geekbrains.krylov.dto.ProductDTO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {

    public List<ProductDTO> findAll();

    public ProductDTO findById(Long id);

    public void saveOrUpdate(ProductDTO productDTO);

    public void deleteById(Long id);
}
