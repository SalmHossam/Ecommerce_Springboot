package com.salma.Ecommerce.Service;

import com.salma.Ecommerce.Entity.User;
import com.salma.Ecommerce.Entity.WebOrder;
import com.salma.Ecommerce.Repository.WebOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebOrderService {

    @Autowired
    private WebOrderRepository webOrderRepository;

    public List<WebOrder> getAllOrders(){
        return webOrderRepository.findAll();
    }

    public List<WebOrder> getAllUserOrders(@AuthenticationPrincipal User user){
        return webOrderRepository.findByUser(user);
    }

}
