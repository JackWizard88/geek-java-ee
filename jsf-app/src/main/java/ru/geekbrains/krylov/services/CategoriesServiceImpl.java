package ru.geekbrains.krylov.services;

import lombok.Data;
import ru.geekbrains.krylov.dto.CategoryDTO;
import ru.geekbrains.krylov.entities.Category;
import ru.geekbrains.krylov.repositories.CategoriesRepository;
import ru.geekbrains.krylov.rest.CategoryServiceRest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Stateless
public class CategoriesServiceImpl implements CategoryServiceRest {

    @EJB
    private CategoriesRepository categoryRepository;

    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    public CategoryDTO findById(Long id) {
        Category category = categoryRepository.findById(id);
        if (category != null) {
            return new CategoryDTO(category);
        }
        return null;
    }

    @Override
    public void insert(CategoryDTO category) {
        saveOrUpdate(category);
    }

    @Override
    public void update(CategoryDTO category) {
        saveOrUpdate(category);
    }

    public void saveOrUpdate(CategoryDTO category) {
        Category c = null;
        if (category.getId() != null) {
            c = categoryRepository.getReference(category.getId());
        }
        categoryRepository.saveOrUpdate(c);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
