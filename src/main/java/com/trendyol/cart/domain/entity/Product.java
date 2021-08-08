package com.trendyol.cart.domain.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.trendyol.cart.domain.exception.UnknownCurrencyCodeException;
import com.trendyol.cart.domain.vo.Money;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class Product {
    private final UUID id;
    private final Money price;
    private final String title;
    private final String image;

    @SneakyThrows
    @JsonCreator
    public Product(@JsonProperty("id") final UUID id,
                   @JsonProperty("price") final BigDecimal price,
                   @JsonProperty("name") final String title,
                   @JsonProperty("image") String image) {
        this.id = id;
        this.price = new Money(price, "TRY");
        this.title = title;
        this.image = image;
    }

    public UUID getId() {
        return id;
    }

    public Money getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(price, product.price) &&
                Objects.equals(title, product.title) && Objects.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, title, image);
    }
}
