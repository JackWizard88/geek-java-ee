package ru.geekbrains.krylov.services;

import ru.geekbrains.krylov.entities.OrderItem;
import ru.geekbrains.krylov.repositories.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateful
public class CartServiceImpl implements CartService {

    private Map<Long, OrderItem> orderItemMap = new HashMap<>();

    @EJB
    private ProductRepository productRepository;

    @Override
    public void addToCart(Long id) {
        OrderItem orderItem = orderItemMap.get(id);

        if (orderItem == null) {
            OrderItem o = new OrderItem();
            o.setProduct(productRepository.findById(id));
            o.setQuantity(1L);
            o.calcOrderItemPrice();
            orderItemMap.put(id, o);
        } else orderItem.incrementQuantity();
    }

    @Override
    public void removeFromCart(Long id) {
        OrderItem orderItem = orderItemMap.get(id);
        orderItem.decrementQuantity();

        if (orderItem.getQuantity() == 0) {
            orderItemMap.remove(id);
        }
    }

    @Override
    public List<OrderItem> getCartContent() {
        return new ArrayList<>(orderItemMap.values());
    }
}
