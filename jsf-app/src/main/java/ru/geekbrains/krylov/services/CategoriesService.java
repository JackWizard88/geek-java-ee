package ru.geekbrains.krylov.services;

import ru.geekbrains.krylov.dto.CategoryDTO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CategoriesService {

    public List<CategoryDTO> findAll();

    public CategoryDTO findById(Long id);

    public void saveOrUpdate(CategoryDTO categoryDTO);

    public void deleteById(Long id);
}
