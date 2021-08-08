package com.trendyol.cart.domain;

import com.trendyol.cart.domain.campaign.Campaign;
import com.trendyol.cart.domain.campaign.CartVisitor;
import com.trendyol.cart.domain.campaign.Visitable;
import com.trendyol.cart.domain.exception.UnknownCurrencyCodeException;
import com.trendyol.cart.domain.vo.CartItem;
import com.trendyol.cart.domain.vo.Level;
import com.trendyol.cart.domain.vo.Money;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Cart implements Visitable<CartVisitor> {
    private UUID id;
    private final UUID userId;
    private final Level userLevel;
    private final List<CartItem> cartItems;
    private Money shippingCost;
    private Money totalCost;

    public Cart(final UUID id, Level level, List<CartItem> cartItems) throws UnknownCurrencyCodeException {
        checkNotNull(cartItems);
        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one order line item");
        }
        this.shippingCost = new Money(4.99, "TRY");
        this.userId = id;
        this.userLevel = level;
        this.cartItems = new ArrayList<>(cartItems);
        this.totalCost = calculateCartItemsCost().add(shippingCost());
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        this.totalCost = this.totalCost.add(cartItem.cost());
    }

    @SneakyThrows
    public void deleteCartItem(UUID id, final UUID productId) {
        CartItem cartItem = cartItems.stream()
                .filter(orderItem -> orderItem.getProductId()
                        .equals(id))
                .findFirst().orElseThrow(Exception::new);
        cartItems.remove(cartItem);
        this.totalCost = totalCost().add(new Money(cartItem.cost().amount().negate(), "TRY"));
    }

    public UUID getId() {
        return id;
    }

    public UUID getCartOwnerId() {
        return userId;
    }

    private Money calculateCartItemsCost() {
        return cartItems.stream()
                .map(CartItem::cost)
                .reduce(Money::add)
                .get();
    }

    public Level userLevel() {
        return userLevel;
    }

    public Money shippingCost() {
        return shippingCost;
    }

    public Money totalCost(Campaign campaign) throws UnknownCurrencyCodeException {
        double discountAmount = applyCampaign(campaign);
        Money totalCostAfterCampaign = totalCost().add(new Money(BigDecimal.valueOf(discountAmount).negate(), "TRY"));
        this.totalCost = totalCostAfterCampaign;

        return totalCostAfterCampaign;
    }

    double applyCampaign(Campaign campaign) {
        return campaign.discount(this);
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public Money totalCost() {
        return totalCost;
    }

    private void checkNotNull(List<CartItem> cartItems) {
        if (cartItems == null) throw new NullPointerException("cartItems cannot be null!");
    }

    @Override
    public void accept(CartVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) && Objects.equals(userId, cart.userId) && userLevel == cart.userLevel &&
                Objects.equals(cartItems, cart.cartItems) && Objects.equals(shippingCost, cart.shippingCost) &&
                Objects.equals(totalCost, cart.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, userLevel, cartItems, shippingCost, totalCost);
    }
}
