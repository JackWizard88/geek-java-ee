package ru.geekbrains.krylov.repositories;

import ru.geekbrains.krylov.entities.Category;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CategoriesRepository {

    List<Category> findAll();

    Category findById(Long id);

    void saveOrUpdate(Category category);

    void deleteById(Long id);

    public Category getReference(Long id);
}
