package com.trendyol.cart.domain.service;

import com.trendyol.cart.domain.Cart;
import com.trendyol.cart.domain.entity.Product;
import com.trendyol.cart.domain.exception.UnknownCurrencyCodeException;
import com.trendyol.cart.domain.repository.CartRepository;
import com.trendyol.cart.domain.vo.CartItem;
import com.trendyol.cart.domain.vo.Level;
import java.util.Collections;
import java.util.UUID;

public class CartService implements ICartService {

    private final CartRepository orderRepository;

    public CartService(CartRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public UUID createCart(Product product) {
        Cart cart = null;
        try {
            cart = new Cart(UUID.randomUUID(),
                    Level.NORMAL,
                    Collections.singletonList(new CartItem(product, 1)));
        } catch (UnknownCurrencyCodeException e) {
            e.printStackTrace();
        }

        orderRepository.save(cart);
        return cart.getId();
    }

    @Override
    public void addProduct(UUID cartId, Product product) {
        Cart cart = orderRepository
                .findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart cannot be found!"));
        cart.addCartItem(new CartItem(product, 1));
    }

    @Override
    public void deleteProduct(UUID cartId, UUID productId) {
        Cart cart = orderRepository
                .findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart cannot be found!"));
        cart.deleteCartItem(cartId, productId);
    }
}
