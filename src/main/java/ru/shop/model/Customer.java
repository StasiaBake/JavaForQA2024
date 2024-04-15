package ru.shop.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Customer {
    private UUID id;
    private String name;
    private String phone;
    private int age;
}
