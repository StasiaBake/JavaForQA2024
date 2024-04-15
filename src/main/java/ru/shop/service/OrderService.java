package ru.shop.service;

import ru.shop.exceptions.BadCountException;
import ru.shop.model.Customer;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.repository.OrderRepository;

import java.util.List;
import java.util.Objects;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void add(Customer customer, Product product, long count) {
        if (count < 1) {
            throw new BadCountException(count);
        }

        var order = new Order();

        order.setCustomerId(customer.getId());
        order.setProductId(product.getId());
        order.setCount(count);
        order.setAmount(count * product.getCost());

        orderRepository.save(order);
    }

    public List<Order> findByCustomer(Customer customer) {
        return findAll().stream()
                .filter(order -> Objects.equals(order.getCustomerId(), customer.getId()))
                .toList();
    }

    public long getTotalCustomerAmount(Customer customer) {
        return findByCustomer(customer)
                .stream().mapToLong(Order::getAmount)
                .sum();
    }
}
