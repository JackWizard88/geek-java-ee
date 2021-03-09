package ru.geekbrains.krylov.repositories;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Repository <T> {

    List<T> findAll();

    T findById(Long id);

    void saveOrUpdate(T t);

    void deleteById(Long id);

    public T getReference(Long id);
}
