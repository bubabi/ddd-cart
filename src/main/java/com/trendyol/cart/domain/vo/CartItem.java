package com.trendyol.cart.domain.vo;

import com.trendyol.cart.domain.entity.Product;
import java.util.Objects;
import java.util.UUID;

public class CartItem {
    private UUID productId;
    private Money itemPrice;
    private int quantity;

    public CartItem(final Product product, int quantity) {
        this.productId = product.getId();
        this.itemPrice = product.getPrice();
        this.quantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public Money getItemPrice() {
        return itemPrice;
    }

    public Money cost() {
        return getItemPrice().multiply(quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem orderItem = (CartItem) o;
        return Objects.equals(productId, orderItem.productId) && Objects.equals(itemPrice, orderItem.itemPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, itemPrice);
    }
}
