package com.grofers.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grofers.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
