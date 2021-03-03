package ru.geekbrains.krylov.repositories;

import ru.geekbrains.krylov.entities.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class CategoriesRepository implements Repository <Category> {

    private final Map<Long, Category> categoryMap = new ConcurrentHashMap<>();
    private final AtomicLong identity = new AtomicLong(0);

    @Override
    public List<Category> findAll() {
        return new ArrayList<>(categoryMap.values());
    }

    @Override
    public Category findById(Long id) {
        return categoryMap.get(id);
    }

    @Override
    public void saveOrUpdate(Category category) {
        if (category.getId() == null) {
            Long id = identity.incrementAndGet();
            category.setId(id);
        }
        categoryMap.put(category.getId(), category);
    }

    @Override
    public Category deleteById(Long id) {
        return categoryMap.remove(id);
    }
}
