package com.trendyol.cart.domain.repository;

import com.trendyol.cart.domain.Cart;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository {
    Optional<Cart> findById(UUID id);

    void save(Cart cart);
}
