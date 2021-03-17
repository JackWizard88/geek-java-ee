package ru.geekbrains.krylov.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.krylov.dto.ProductDTO;

import javax.persistence.*;


@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "from Product"),
        @NamedQuery(name = "deleteById", query = "delete from Product p where p.id = :id")
})
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @Column(length = 1024)
    private String description;

    @Column
    private Float price;

    public Product(Long id, String title, String description, Float price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Product(ProductDTO product, Category category) {
        this(product.getId(), product.getTitle(), product.getDescription(), product.getPrice());
        this.category = category;
    }
}
