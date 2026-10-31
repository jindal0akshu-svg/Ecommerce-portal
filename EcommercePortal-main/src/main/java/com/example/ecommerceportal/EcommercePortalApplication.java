package com.example.ecommerceportal;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ecommerceportal.entity.Customer;
import com.example.ecommerceportal.entity.Order;
import com.example.ecommerceportal.entity.Product;
import com.example.ecommerceportal.repository.CustomerRepository;
import com.example.ecommerceportal.repository.ProductRepository;

@SpringBootApplication
public class EcommercePortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommercePortalApplication.class, args);
    }

    @Bean
    CommandLineRunner run(
            CustomerRepository customerRepository,
            ProductRepository productRepository) {

        return args -> {

            // Create customer
            Customer customer = new Customer(
                    "Rohan",
                    "rohan@gmail.com");

            // Create orders
            Order order1 = new Order(
                    "ORD101",
                    new BigDecimal("2500.00"),
                    LocalDate.now());

            Order order2 = new Order(
                    "ORD102",
                    new BigDecimal("1500.00"),
                    LocalDate.now());

            // Establish one-to-many relationship
            customer.addOrder(order1);
            customer.addOrder(order2);

            // Save customer and associated orders
            Customer savedCustomer =
                    customerRepository.save(customer);

            System.out.println("\n--- Customer Saved ---");
            System.out.println(savedCustomer);

            System.out.println("\n--- Customer Orders ---");
            savedCustomer.getOrders().forEach(
                    System.out::println);

            // CREATE product
            Product product = new Product(
                    "Laptop",
                    new BigDecimal("55000.00"),
                    10);

            Product savedProduct =
                    productRepository.save(product);

            System.out.println("\n--- Product Created ---");
            System.out.println(savedProduct);

            // READ product
            System.out.println("\n--- All Products ---");
            productRepository.findAll().forEach(
                    System.out::println);

            // UPDATE product
            savedProduct.setPrice(
                    new BigDecimal("52000.00"));

            savedProduct.setQuantity(8);

            productRepository.save(savedProduct);

            System.out.println("\n--- Product Updated ---");
            System.out.println(
                    productRepository.findById(savedProduct.getId())
                            .orElseThrow());


             productRepository.deleteById(savedProduct.getId());
        };
    }
}