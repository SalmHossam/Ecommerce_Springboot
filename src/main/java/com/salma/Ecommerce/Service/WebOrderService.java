package com.salma.Ecommerce.Service;

import com.salma.Ecommerce.Entity.WebOrder;
import com.salma.Ecommerce.Repository.WebOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebOrderService {

    @Autowired
    private WebOrderRepository webOrderRepository;

    public List<WebOrder> getAllOrders(){
        return webOrderRepository.findAll();
    }
}
