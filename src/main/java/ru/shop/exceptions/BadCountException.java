package ru.shop.exceptions;

public class BadCountException extends RuntimeException {
    public BadCountException(long orderCount) {
        super("Order count must be positive: " + orderCount);
    }
}
