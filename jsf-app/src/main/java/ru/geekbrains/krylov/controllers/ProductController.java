package ru.geekbrains.krylov.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.geekbrains.krylov.entities.Category;
import ru.geekbrains.krylov.entities.Product;
import ru.geekbrains.krylov.repositories.CategoriesRepository;
import ru.geekbrains.krylov.repositories.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    private final static Logger logger = LogManager.getLogger(ProductController.class);


    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoriesRepository categoriesRepository;

    @Inject
    private CartController cartController;

    private Product product;
    private List<Product> productList;
    private List<Category> categoryList;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        productList = productRepository.findAll();
        categoryList = categoriesRepository.findAll();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new Product();
        product.setCategory(new Category());
        return "/product_form.xhtml?faces-redirect=true";
    }

    public List<Product> getAllProducts() {
        return productList;
    }

    public List<Category> getAllCategories() {
        return categoryList;
    }

    public String editProduct(Product product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product) {
        productRepository.deleteById(product.getId());
    }

    public String saveProduct() {
        productRepository.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect=true";
    }
}
