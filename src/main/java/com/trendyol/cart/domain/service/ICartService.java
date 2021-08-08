package com.trendyol.cart.domain.service;

import com.trendyol.cart.domain.entity.Product;

import java.util.UUID;

public interface ICartService {
    UUID createCart(Product product);

    void addProduct(UUID cartId, Product product);

    void deleteProduct(UUID cartId, UUID productId);
}
