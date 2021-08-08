package com.trendyol.cart.domain.campaign;

import com.trendyol.cart.domain.Cart;

public interface Campaign {
    double discount(Cart cart);
}
