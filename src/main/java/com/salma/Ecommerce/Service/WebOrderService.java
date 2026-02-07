package com.salma.Ecommerce.Service;

import com.salma.Ecommerce.DTO.OrderItemDTO;
import com.salma.Ecommerce.DTO.WebOrderDTO;
import com.salma.Ecommerce.Entity.*;
import com.salma.Ecommerce.Repository.AddressRepository;
import com.salma.Ecommerce.Repository.ProductRepository;
import com.salma.Ecommerce.Repository.UserRepository;
import com.salma.Ecommerce.Repository.WebOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WebOrderService {

    @Autowired
    private WebOrderRepository webOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProductRepository productRepository;


    public List<WebOrder> getAllOrders(){
        return webOrderRepository.findAll();
    }

    public List<WebOrder> getAllUserOrders(@AuthenticationPrincipal User user){
        return webOrderRepository.findByUser(user);
    }
    public WebOrder createOrder(WebOrderDTO webOrderDTO) {
        WebOrder webOrder = new WebOrder();

        User user = userRepository.findById(webOrderDTO.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        webOrder.setUser(user);


        Address address = addressRepository.findById(webOrderDTO.getAddressId())
                .orElseThrow(() -> new RuntimeException("Address not found"));
        webOrder.setAddress(address);


        List<WebOrderQuantities> quantities = new ArrayList<>();
        for (OrderItemDTO itemDTO : webOrderDTO.getItems()) {
            WebOrderQuantities quantity = new WebOrderQuantities();
            quantity.setOrder(webOrder);
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            quantity.setProduct(product);
            quantity.setQuantity(itemDTO.getQuantity());
            quantities.add(quantity);
        }
        webOrder.setQuantities(quantities);


        webOrder.setTotalPrice(webOrderDTO.getTotalPrice());

        return webOrderRepository.save(webOrder);
    }

    public WebOrder updateOrder(Long orderId, WebOrderDTO webOrderDTO) {

        WebOrder webOrder = webOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));


        if (webOrderDTO.getUser() != null) {
            User user = userRepository.findById(webOrderDTO.getUser().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            webOrder.setUser(user);
        }


        if (webOrderDTO.getAddressId() != null) {
            Address address = addressRepository.findById(webOrderDTO.getAddressId())
                    .orElseThrow(() -> new RuntimeException("Address not found"));
            webOrder.setAddress(address);
        }


        if (webOrderDTO.getTotalPrice() != null) {
            webOrder.setTotalPrice(webOrderDTO.getTotalPrice());
        }


        if (webOrderDTO.getItems() != null && !webOrderDTO.getItems().isEmpty()) {

            webOrder.getQuantities().clear();

            for (OrderItemDTO itemDTO : webOrderDTO.getItems()) {
                WebOrderQuantities quantity = new WebOrderQuantities();
                quantity.setOrder(webOrder);

                Product product = productRepository.findById(itemDTO.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));
                quantity.setProduct(product);

                quantity.setQuantity(itemDTO.getQuantity());
                webOrder.getQuantities().add(quantity);
            }
        }


        return webOrderRepository.save(webOrder);
    }


}
