package ru.geekbrains.krylov.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.geekbrains.krylov.dto.ProductDTO;
import ru.geekbrains.krylov.entities.Category;
import ru.geekbrains.krylov.repositories.CategoriesRepository;
import ru.geekbrains.krylov.services.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    private final static Logger logger = LogManager.getLogger(ProductController.class);

    @Inject
    private HttpSession httpSession;

    @EJB
    private ProductService productService;

    @EJB
    private CategoriesRepository categoriesRepositoryImpl;

    @Inject
    private CartController cartController;

    private ProductDTO product;
    private List<ProductDTO> productList;
    private List<Category> categoryList;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        productList = productService.findAll();
        categoryList = categoriesRepositoryImpl.findAll();
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new ProductDTO();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public List<ProductDTO> getAllProducts() {
        return productList;
    }

    public List<Category> getAllCategories() {
        return categoryList;
    }

    public String editProduct(ProductDTO product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(ProductDTO product) {
        productService.deleteById(product.getId());
    }

    public String saveProduct() {
        productService.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect=true";
    }

    public String logout() {
        httpSession.invalidate();
        return "/login.xhtml?faces-redirect=true";
    }
}
