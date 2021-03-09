package ru.geekbrains.krylov.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.krylov.entities.Category;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CategoryDTO {

    private Long id;

    private String title;

    private String description;

    private List<ProductDTO> products;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.title = category.getTitle();
        this.description = category.getDescription();
        this.products = category.getProducts().stream().map(ProductDTO::new).collect(Collectors.toList());
    }
}
