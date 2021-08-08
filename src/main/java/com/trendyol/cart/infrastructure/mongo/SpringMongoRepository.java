package com.trendyol.cart.infrastructure.mongo;

import com.trendyol.cart.domain.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringMongoRepository extends MongoRepository<Cart, UUID> {
}
