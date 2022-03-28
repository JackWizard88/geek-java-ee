package ru.geekbrains.krylov.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.krylov.entities.Product;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class ProductDTO implements Serializable {

    private Long id;

    private String title;

    private String description;

    private Float price;

    private Long categoryId;

    private String categoryTitle;


    public ProductDTO(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.categoryId = product.getCategory().getId();
        this.categoryTitle = product.getCategory().getTitle();
    }
}
