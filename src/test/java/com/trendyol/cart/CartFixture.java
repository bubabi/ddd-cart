package com.trendyol.cart;

import com.trendyol.cart.domain.entity.Product;
import com.trendyol.cart.domain.exception.UnknownCurrencyCodeException;
import com.trendyol.cart.domain.vo.CartItem;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CartFixture {

    static Product p1 = new Product(UUID.randomUUID(),
            BigDecimal.valueOf(40),
            "Product1",
            "imageURL1");

    static Product p2 = new Product(UUID.randomUUID(),
            BigDecimal.valueOf(50),
            "Product2",
            "imageURL2");

    static Product p3 = new Product(UUID.randomUUID(),
            BigDecimal.valueOf(100),
            "Product3",
            "imageURL3");

    public CartFixture() throws UnknownCurrencyCodeException {
    }

    public static List<CartItem> cartItemsMinWorthAppliesShippingCampaign() {
        return Collections.singletonList(new CartItem(p3, 1));
    }
}
