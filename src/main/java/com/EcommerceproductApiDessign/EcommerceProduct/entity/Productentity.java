package com.EcommerceproductApiDessign.EcommerceProduct.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productdetails")
@Builder
public class Productentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productname;

    private String description;

    private Double ratings;

    private Long totalbuy;

    private Double price;

    private String imageurl;

    private Integer stockQuantity;

    private String category;

    private String brand;

    private Boolean inStock;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
