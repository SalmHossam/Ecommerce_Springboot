package com.salma.Ecommerce.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {

    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer quantity;
}
