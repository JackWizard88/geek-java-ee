package ru.geekbrains.krylov.services;

import lombok.Data;
import ru.geekbrains.krylov.dto.ProductDTO;
import ru.geekbrains.krylov.entities.Category;
import ru.geekbrains.krylov.entities.Product;
import ru.geekbrains.krylov.repositories.CategoriesRepository;
import ru.geekbrains.krylov.repositories.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Stateless
public class ProductServiceImpl implements ProductService {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoriesRepository categoryRepository;

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    public ProductDTO findById(Long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            return new ProductDTO(product);
        }
        return null;
    }

    public void saveOrUpdate(ProductDTO product) {

        Category category = null;
        if (product.getCategoryId() != null) {
            category = categoryRepository.getReference(product.getCategoryId());
        }
        productRepository.saveOrUpdate(new Product(product, category));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
