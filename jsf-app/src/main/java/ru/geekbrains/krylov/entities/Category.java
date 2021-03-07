package ru.geekbrains.krylov.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "findAllCategories", query = "from Category"),
        @NamedQuery(name = "deleteCategoryById", query = "delete from Category c where c.id = :id")
})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column(length = 1024)
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
