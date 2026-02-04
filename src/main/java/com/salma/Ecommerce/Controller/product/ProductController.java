package com.salma.Ecommerce.Controller.product;

import com.salma.Ecommerce.Entity.Product;
import com.salma.Ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails){
        Product existingProduct = productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(productDetails.getName());
        existingProduct.setPrice(productDetails.getPrice());


        Product updatedProduct = productService.updateProduct(existingProduct);
        return ResponseEntity.ok(updatedProduct);
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @GetMapping("/products")
    public ResponseEntity<Optional<Product>> getProductByName(@PathVariable String name){
        return ResponseEntity.ok(productService.getProductByName(name));
    }

}
