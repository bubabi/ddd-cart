package com.trendyol.cart.infrastructure.mongo;

import com.trendyol.cart.domain.Cart;
import com.trendyol.cart.domain.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Primary
public class MongoCartRepository implements CartRepository {

    private final SpringMongoRepository cartRepository;

    @Autowired
    public MongoCartRepository(SpringMongoRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<Cart> findById(UUID id) {
        return cartRepository.findById(id);
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }
}
