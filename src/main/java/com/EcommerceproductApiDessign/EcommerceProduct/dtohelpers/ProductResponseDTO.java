package com.EcommerceproductApiDessign.EcommerceProduct.dtohelpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {

    private String productname;

    private String description;

    private Double ratings;

    private Long totalbuy;

    private Double price;

    private String imageurl;

    private Integer stockQuantity;

    private String category;

    private String brand;
}
