package com.trendyol.cart.domain.campaign;

import com.trendyol.cart.domain.Cart;

import java.math.BigDecimal;

public class ShippingCampaign implements Campaign {

    private final BigDecimal SHIPPING_DISCOUNT_LIMIT = BigDecimal.valueOf(100);

    @Override
    public double discount(Cart cart) {
        BigDecimal currentCartCost = cart.totalCost().amount();

        if (currentCartCost.compareTo(SHIPPING_DISCOUNT_LIMIT) > 0) {
            return cart.shippingCost().amount().doubleValue();
        } else {
            return 0;
        }
    }
}
