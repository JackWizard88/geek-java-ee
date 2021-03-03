package ru.geekbrains.krylov.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private Long id;
    private Product product;
    private Long quantity;
    private Float orderItemPrice;

    public void calcOrderItemPrice() {
        orderItemPrice = product.getPrice() * quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public void decrementQuantity() {
        if (this.quantity >= 1) {
            this.quantity--;
        }
    }
}
