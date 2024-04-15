package ru.shop.service;

import ru.shop.model.Customer;
import ru.shop.repository.CustomerRepository;

import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }


}
