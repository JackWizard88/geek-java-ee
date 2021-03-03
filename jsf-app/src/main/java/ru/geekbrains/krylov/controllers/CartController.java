package ru.geekbrains.krylov.controllers;

import ru.geekbrains.krylov.entities.OrderItem;
import ru.geekbrains.krylov.entities.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class CartController implements Serializable {

    private Map<Product, OrderItem> orderItemMap = new HashMap<>();
    private List<OrderItem> orderItems;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderItem> getCartContent() {
        return new ArrayList<>(orderItemMap.values());
    }

    public void addToCart(Product product) {
        OrderItem orderItem = orderItemMap.get(product);

        if (orderItem == null) {
            OrderItem o = new OrderItem();
            o.setProduct(product);
            o.setQuantity(1L);
            o.calcOrderItemPrice();
            orderItemMap.put(product, o);
        } else orderItem.incrementQuantity();

     }

    public void removeFromCart(Product product) {
        OrderItem orderItem = orderItemMap.get(product);
        orderItem.decrementQuantity();

        if (orderItem.getQuantity() == 0) {
            orderItemMap.remove(product);
        }
    }
}
