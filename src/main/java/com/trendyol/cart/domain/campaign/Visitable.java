package com.trendyol.cart.domain.campaign;

public interface Visitable<V> {
    void accept(V visitor);
}