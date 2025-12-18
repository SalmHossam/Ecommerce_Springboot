package com.salma.Ecommerce.Controller.order;

import com.salma.Ecommerce.Entity.User;
import com.salma.Ecommerce.Entity.WebOrder;
import com.salma.Ecommerce.Service.WebOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WebOrderController {

    @Autowired
    private WebOrderService webOrderService;

    @GetMapping("/orders")
    public ResponseEntity<List<WebOrder>> getAllOrders(){
        return ResponseEntity.ok(webOrderService.getAllOrders());
    }

    @GetMapping("/orders/user")
    public ResponseEntity<List<WebOrder>> getAllUserOrders(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(webOrderService.getAllUserOrders(user));
    }
}
