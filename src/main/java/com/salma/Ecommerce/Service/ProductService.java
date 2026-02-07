package com.salma.Ecommerce.Service;

import com.salma.Ecommerce.Entity.Inventory;
import com.salma.Ecommerce.Entity.Product;
import com.salma.Ecommerce.Repository.InventoryRepository;
import com.salma.Ecommerce.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Optional<Product> getProductByName(String name){
        return productRepository.findByName(name);
    }
    public Optional<Product> getProductById(long id){
        return productRepository.findById(id);
    }
    public Product createProduct(Product product) {

        Inventory inventory = product.getInventory();
        if (inventory != null) {

            inventory = inventoryRepository.save(inventory);
            product.setInventory(inventory);
        }

        return productRepository.save(product);
    }
    public Product updateProduct(Product product){
        return productRepository.save(product);
    }
    public void deleteProduct(long id){
        Product product=productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

}
