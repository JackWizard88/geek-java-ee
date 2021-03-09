package ru.geekbrains.krylov.services;

import java.util.List;

public interface Service<T> {

    public List<T> findAll();

    public T findById(Long id);

    public void saveOrUpdate(T t);

    public void deleteById(Long id);
}
