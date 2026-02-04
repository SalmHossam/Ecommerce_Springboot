package com.salma.Ecommerce.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private Long id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private Double price;
    private Integer availableQuantity;
}

