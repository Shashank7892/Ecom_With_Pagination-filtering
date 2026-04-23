package com.EcommerceproductApiDessign.EcommerceProduct.dtohelpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductDTO {

    private String productname;

    private String description;

    private Double price;

    private String imageurl;

    private Integer stockQuantity;

    private String category;

    private String brand;

}
