package com.example.spring.springcloud.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.springcloud.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

	Product findByName(String name);

}
