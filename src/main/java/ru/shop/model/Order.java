package ru.shop.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Order {
    private UUID id;
    private UUID customerId;
    private UUID productId;
    private long count;
    private long amount;
}
