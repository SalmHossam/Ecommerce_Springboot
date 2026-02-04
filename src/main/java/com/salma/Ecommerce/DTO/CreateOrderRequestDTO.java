package com.salma.Ecommerce.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderRequestDTO {

    private Long addressId;
    private List<ProductQuantityDTO> products;
}
