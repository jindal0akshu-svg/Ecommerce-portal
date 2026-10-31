package com.example.ecommerceportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerceportal.entity.Customer;

public interface CustomerRepository
        extends JpaRepository<Customer, Long> {

}