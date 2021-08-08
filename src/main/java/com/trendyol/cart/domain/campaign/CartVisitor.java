package com.trendyol.cart.domain.campaign;

import com.trendyol.cart.domain.Cart;

public interface CartVisitor {
    void visit(Cart cart);
}
