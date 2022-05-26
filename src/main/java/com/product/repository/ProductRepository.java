package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.google.common.base.Optional;
import com.product.model.Product;



public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);
    
    Product findById(int id); 
}

