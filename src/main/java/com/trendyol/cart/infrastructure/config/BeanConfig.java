package com.trendyol.cart.infrastructure.config;

import com.trendyol.cart.CartApplication;
import com.trendyol.cart.domain.repository.CartRepository;
import com.trendyol.cart.domain.service.CartService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = CartApplication.class)
public class BeanConfig {

    @Bean
    CartService cartService(final CartRepository cartRepository) {
        return new CartService(cartRepository);
    }
}
