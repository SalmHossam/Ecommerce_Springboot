package com.salma.Ecommerce.Controller.product;

import com.salma.Ecommerce.Entity.Product;
import com.salma.Ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        if(productService.getAllProducts()!=null){
            return ResponseEntity.ok(productService.getAllProducts());
        }
        else{
            return ResponseEntity.notFound().build();
        }

    }
}
