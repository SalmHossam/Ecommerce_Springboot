package com.salma.Ecommerce.Service;

import com.salma.Ecommerce.Entity.Product;
import com.salma.Ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Optional<Product> getProductByName(String name){
        return productRepository.findByName(name);
    }
}
