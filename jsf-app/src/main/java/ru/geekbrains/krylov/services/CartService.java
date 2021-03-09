package ru.geekbrains.krylov.services;

import ru.geekbrains.krylov.entities.OrderItem;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CartService {

    void addToCart(Long id);

    void removeFromCart(Long id);

    List<OrderItem> getCartContent();
}
