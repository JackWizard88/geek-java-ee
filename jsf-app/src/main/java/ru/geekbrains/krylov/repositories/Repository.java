package ru.geekbrains.krylov.repositories;

import java.util.List;

public interface Repository <T> {

    List<T> findAll();

    T findById(Long id);

    void saveOrUpdate(T t);

    T deleteById(Long id);
}
