package ru.shop;

import ru.shop.model.Customer;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.model.ProductType;
import ru.shop.repository.CustomerRepository;
import ru.shop.repository.OrderRepository;
import ru.shop.repository.ProductRepository;

import java.util.UUID;

public class Main {
    private static final ProductRepository productRepository = new ProductRepository();
    private static final CustomerRepository customerRepository = new CustomerRepository();
    private static final OrderRepository orderRepository = new OrderRepository();

    private static final String CONST = "CONST";
    private static final long LONG_CONST = 99999999999L;

    public static void main(String[] args) {
            var product = new Product();

            product.setId(UUID.randomUUID());
            product.setName("product1");
            product.setCost(99);
            product.setProductType(ProductType.GOOD);

            System.out.println("product = " + product);

            var product2 = new Product();

            product2.setId(UUID.randomUUID());
            product2.setName("product2");
            product2.setCost(55);
            product2.setProductType(ProductType.GOOD);

            System.out.println("product2 = " + product2);

            productRepository.save(product);
            productRepository.save(product2);

            var allProducts = productRepository.findAll();

            System.out.println("products = " + allProducts);

            var customer = new Customer();
            customer.setId(UUID.randomUUID());
            customer.setName("customer1");
            customer.setAge(33);
            customer.setPhone("89997776655");
            customerRepository.save(customer);

            var customer2 = new Customer();
            customer2.setId(UUID.randomUUID());
            customer2.setName("customer2");
            customer2.setAge(19);
            customer2.setPhone("89997776655");
            customerRepository.save(customer2);

            System.out.println("customers = " + customerRepository.findAll());

            var order = new Order();

            order.setId(UUID.randomUUID());
            order.setCustomerId(customer.getId());
            order.setProductId(product.getId());
            order.setCount(1);
            order.setAmount(1000);
            orderRepository.save(order);

            var order2 = new Order();

            order2.setId(UUID.randomUUID());
            order2.setCustomerId(customer2.getId());
            order2.setProductId(product2.getId());
            order2.setCount(1);
            order2.setAmount(500);
            orderRepository.save(order2);

            System.out.println("orders = " + orderRepository.findAll());
            System.out.println("orders for customer = " + orderRepository.findCustomerOrders(customer.getId()));
    }

}