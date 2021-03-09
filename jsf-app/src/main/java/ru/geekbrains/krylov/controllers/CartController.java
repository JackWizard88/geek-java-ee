package ru.geekbrains.krylov.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.geekbrains.krylov.entities.OrderItem;
import ru.geekbrains.krylov.services.CartService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {

    private final static Logger logger = LogManager.getLogger(CartController.class);

    @EJB
    private CartService cartService;

    public List<OrderItem> getCartContent() {
        return cartService.getCartContent();
    }

    public void addToCart(Long id) {
        cartService.addToCart(id);
    }

    public void removeFromCart(Long id) {
        cartService.removeFromCart(id);
    }
}
