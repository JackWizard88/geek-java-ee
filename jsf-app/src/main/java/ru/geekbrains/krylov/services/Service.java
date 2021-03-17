package ru.geekbrains.krylov.services;

import java.util.List;

public interface Service<T> {

    List<T> findAll();

    T findById(Long id);

    void saveOrUpdate(T t);

    void deleteById(Long id);
}
