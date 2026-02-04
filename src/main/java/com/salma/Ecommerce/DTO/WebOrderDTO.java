package com.salma.Ecommerce.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WebOrderDTO {

    private Long orderId;
    private UserDTO user;
    private Long addressId;
    private List<OrderItemDTO> items;
    private Double totalPrice;
}
