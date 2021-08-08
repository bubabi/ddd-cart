package com.trendyol.cart;

import com.trendyol.cart.domain.Cart;
import com.trendyol.cart.domain.campaign.Campaign;
import com.trendyol.cart.domain.campaign.LevelCampaign;
import com.trendyol.cart.domain.campaign.ShippingCampaign;
import com.trendyol.cart.domain.vo.CartItem;
import com.trendyol.cart.domain.vo.Level;
import com.trendyol.cart.domain.vo.Money;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.beans.FixedKeySet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CartApplicationTests {

    @Test
    void testShippingCampaign() throws Exception {

        Cart cart = new Cart(UUID.randomUUID(), Level.NORMAL, CartFixture.cartItemsMinWorthAppliesShippingCampaign());

        Campaign shippingCampaign = new ShippingCampaign();

        Money totalCostAfterShippingCampaign = cart.totalCost(shippingCampaign);

        assertThat(totalCostAfterShippingCampaign.amount().doubleValue()).isEqualTo(100.0);
    }

    @Test
    void testUserLevelCampaign() throws Exception {

        Cart cart = new Cart(UUID.randomUUID(), Level.ELITE, Arrays.asList(
                new CartItem(CartFixture.p1, 1),
                new CartItem(CartFixture.p2, 1),
                new CartItem(CartFixture.p3, 1)));

        Campaign shippingCampaign = new ShippingCampaign();
        Campaign levelCampaign = new LevelCampaign();

        cart.totalCost(shippingCampaign);
        cart.totalCost(levelCampaign);

        double totalCostAfterLevelCampaign = cart.totalCost().amount().doubleValue();

        assertThat(totalCostAfterLevelCampaign).isEqualTo(161.5);
    }

}
