package com.trendyol.cart.domain.campaign;

import com.trendyol.cart.domain.Cart;
import com.trendyol.cart.domain.vo.Level;

public class LevelCampaign implements Campaign {

    private final double ELITE_DISCOUNT_RATE = 0.15;
    private final double GOLD_DISCOUNT_RATE = 0.10;
    private final double SILVER_DISCOUNT_RATE = 0.05;

    @Override
    public double discount(Cart cart) {
        if (cart.userLevel() == Level.ELITE) {
            return cart.totalCost().multiply(ELITE_DISCOUNT_RATE).amount().doubleValue();
        } else if (cart.userLevel() == Level.GOLD) {
            return cart.totalCost().multiply(GOLD_DISCOUNT_RATE).amount().doubleValue();
        } else if (cart.userLevel() == Level.SILVER) {
            return cart.totalCost().multiply(SILVER_DISCOUNT_RATE).amount().doubleValue();
        } else return 0;
    }
}
