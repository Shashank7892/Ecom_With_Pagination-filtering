package com.EcommerceproductApiDessign.EcommerceProduct.controller;

import com.EcommerceproductApiDessign.EcommerceProduct.dtohelpers.AddProductDTO;
import com.EcommerceproductApiDessign.EcommerceProduct.dtohelpers.PaginatedResponse;
import com.EcommerceproductApiDessign.EcommerceProduct.dtohelpers.ProductResponseDTO;
import com.EcommerceproductApiDessign.EcommerceProduct.entity.Productentity;
import com.EcommerceproductApiDessign.EcommerceProduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class Productcontroller {

    @Autowired
    private ProductService productService;

    @PostMapping("/addproduct")
    public ResponseEntity<String> RegisterUser(@RequestBody AddProductDTO productDTO){
        String response = productService.addproduct(productDTO);
        return new ResponseEntity<String>(response, HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public ResponseEntity<PaginatedResponse<ProductResponseDTO>> getallproducts( @RequestParam(required = false) String category,
                                                                            @RequestParam(required = false) String brand,
                                                                            @RequestParam(required = false) Double minPrice,
                                                                            @RequestParam(required = false) Double maxPrice,
                                                                            @RequestParam(defaultValue = "0") int page,
                                                                           @RequestParam(defaultValue = "10") int size,
                                                                           @RequestParam(defaultValue = "createdAt") String sortBy,
                                                                           @RequestParam(defaultValue = "desc")  String direction){

        PaginatedResponse<ProductResponseDTO> response = productService.getallproducts(category,brand,minPrice,maxPrice,page,size,sortBy,direction);
        return new ResponseEntity<PaginatedResponse<ProductResponseDTO>>(response, HttpStatus.OK);
    }
}
