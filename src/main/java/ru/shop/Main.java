package ru.shop;

import ru.shop.exceptions.BadCountException;
import ru.shop.model.Customer;
import ru.shop.model.Order;
import ru.shop.model.Product;
import ru.shop.model.ProductType;
import ru.shop.repository.CustomerRepository;
import ru.shop.repository.OrderRepository;
import ru.shop.repository.ProductRepository;
import ru.shop.service.CustomerService;
import ru.shop.service.OrderService;
import ru.shop.service.ProductService;

import java.util.UUID;

public class Main {
    private static final ProductRepository productRepository = new ProductRepository();
    private static final CustomerRepository customerRepository = new CustomerRepository();
    private static final OrderRepository orderRepository = new OrderRepository();

    private static final ProductService productService = new ProductService(productRepository);
        private static final CustomerService customerService = new CustomerService(customerRepository);
        private static final OrderService orderService = new OrderService(orderRepository);

    private static final String CONST = "CONST";
    private static final long LONG_CONST = 99999999999L;

    public static void main(String[] args) {
        var product = new Product();

        product.setId(UUID.randomUUID());
        product.setName("product1");
        product.setCost(99);
        product.setProductType(ProductType.SERVICE);

        var product2 = new Product();

        product2.setId(UUID.randomUUID());
        product2.setName("product2");
        product2.setCost(55);
        product2.setProductType(ProductType.GOOD);

        productService.save(product);
        productService.save(product2);

        var allProducts = productService.findAll();

        System.out.println("products = " + allProducts);

        var customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setName("customer1");
        customer.setAge(33);
        customer.setPhone("89997776655");
        customerService.save(customer);

        var customer2 = new Customer();
        customer2.setId(UUID.randomUUID());
        customer2.setName("customer2");
        customer2.setAge(19);
        customer2.setPhone("89997776655");
        customerService.save(customer2);

        System.out.println("customers = " + customerService.findAll());

        orderService.add(customer, product, 1);
        orderService.add(customer2, product2, 1);

        try {
            orderService.add(customer, product2, -5);
        } catch (BadCountException bce) {
            System.out.println("Произошла ошибка: " + bce.getMessage());
        }

        System.out.println("customer count: " + customerService.findAll().size());
        System.out.println("total orders count: " + orderService.findAll().size());
        System.out.println("total products count: " + productService.findAll().size());
        System.out.println("products with type GOOD: " + productService.findByProductType(ProductType.GOOD));
        System.out.println("products with type SERVICE: " + productService.findByProductType(ProductType.SERVICE));
        System.out.println("orders count for customer 1: " + orderService.findByCustomer(customer).size());
        System.out.println("orders count for customer 2: " + orderService.findByCustomer(customer2).size());
    }

}