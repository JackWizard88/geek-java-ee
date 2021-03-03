package ru.geekbrains.krylov.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "from Product"),
        @NamedQuery(name = "countAll", query = "select count(*) from Product"),
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

    public Product(String title, Category category, String description, Float price) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.price = price;
    }
}
