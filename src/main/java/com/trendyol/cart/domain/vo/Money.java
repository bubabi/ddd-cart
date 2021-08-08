package com.trendyol.cart.domain.vo;

import com.trendyol.cart.domain.exception.UnknownCurrencyCodeException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.math.RoundingMode.HALF_UP;

public class Money implements Serializable {

    private static final int[] cents = new int[]{1, 10, 100, 1000};

    private final BigDecimal amount;

    private final Currency currency;

    private final MathContext DEFAULT_CONTEXT = new MathContext(10, RoundingMode.HALF_DOWN);

    public Money(long amount, Currency currency) {
        this.currency = currency;
        this.amount = BigDecimal.valueOf(amount, currency.getDefaultFractionDigits());
    }

    public Money(long amount, String currCode) throws UnknownCurrencyCodeException {
        this(amount, Currency.getInstance(currCode));
    }

    public Money(double amount, Currency curr) {
        this.currency = curr;
        BigDecimal bd = BigDecimal.valueOf(amount);
        this.amount = bd.setScale(centFactor(), HALF_UP);
    }

    public Money(double amount, String currCode) throws UnknownCurrencyCodeException {
        this.currency = Currency.getInstance(currCode);
        BigDecimal bd = BigDecimal.valueOf(amount);
        this.amount = bd.setScale(currency.getDefaultFractionDigits(), HALF_UP);
    }

    public Money(BigDecimal bigDecimal, String currCode) throws UnknownCurrencyCodeException {
        this.currency = Currency.getInstance(currCode);
        this.amount = bigDecimal.setScale(currency.getDefaultFractionDigits(), HALF_UP);
    }

    public Money(BigDecimal bigDecimal, Currency currency) {
        this.currency = currency;
        this.amount = bigDecimal.setScale(currency.getDefaultFractionDigits(), HALF_UP);
    }

    public Money multiply(BigDecimal amount) {
        return new Money(this.amount().multiply(amount, DEFAULT_CONTEXT), currency);
    }

    public Money multiply(BigDecimal amount, RoundingMode roundingMode) {
        MathContext ct = new MathContext(currency.getDefaultFractionDigits(), roundingMode);
        return new Money(amount().multiply(amount, ct), currency);
    }

    public Money multiply(double amount) {
        return multiply(new BigDecimal(amount));
    }

    public BigDecimal amount() {
        return amount;
    }

    public long amountAsLong() {
        return amount.unscaledValue().longValue();
    }

    public Currency getCurrency() {
        return currency;
    }

    public static Money dollars(double amount) {
        Money result = null;
        try {
            result = new Money(amount, "USD");
        } catch (UnknownCurrencyCodeException ex) {
            Logger.getLogger(Money.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static Money dollars(long amount) {
        Money result = null;
        try {
            result = new Money(amount, "USD");
        } catch (UnknownCurrencyCodeException ex) {
            Logger.getLogger(Money.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static Money liras(double amount) {
        Money result = null;
        try {
            result = new Money(amount, "TRY");
        } catch (UnknownCurrencyCodeException ex) {
            Logger.getLogger(Money.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static Money liras(long amount) {
        Money result = null;
        try {
            result = new Money(amount, "TRY");
        } catch (UnknownCurrencyCodeException ex) {
            Logger.getLogger(Money.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private int centFactor() {
        return cents[getCurrency().getDefaultFractionDigits()];
    }

    @Override
    public int hashCode() {
        return (int) (amount.hashCode() ^ (amount.hashCode() >>> 32));
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Money && equals((Money) other));
    }

    public boolean equals(Money other) {
        return (currency.equals(other.currency) && (amount.equals(other.amount)));
    }

    public Money add(Money other) {
        return newMoney(amount.add(other.amount, DEFAULT_CONTEXT));
    }

    private Money newMoney(BigDecimal amount) {
        return new Money(amount, this.currency);
    }

    public String getCurrencyCode() {
        return currency.getCurrencyCode();
    }

    @Override
    public String toString() {
        return amount.toString();
    }
}

