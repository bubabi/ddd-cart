package com.trendyol.cart.domain.exception;

public class UnknownCurrencyCodeException extends Exception {

    private String reason = null;

    public UnknownCurrencyCodeException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public String toString() {
        return getReason();
    }
}
