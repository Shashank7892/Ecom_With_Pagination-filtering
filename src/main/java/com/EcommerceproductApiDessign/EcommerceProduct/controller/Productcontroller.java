package com.EcommerceproductApiDessign.EcommerceProduct.controller;

import com.EcommerceproductApiDessign.EcommerceProduct.dtohelpers.*;
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

    @GetMapping("/product/{productid}")
    public ResponseEntity<ProductResponseDTO> getproductById(@PathVariable Long productid){
        ProductResponseDTO response=productService.getByProductid(productid);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/product/{productid}")
    public ResponseEntity<String> deleteproduct(@PathVariable Long productid){
        String response= productService.deleteByproductid(productid);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/product/{productid}/price")
    public ResponseEntity<String> updateproductprice(@PathVariable Long productid, @RequestBody PriceUpdateDTO productDTO){
        String response= productService.updateProductprice(productid,productDTO);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/product/{productid}/stock")
    public ResponseEntity<String> updateproductstock(@PathVariable Long productid, @RequestBody UpdateStockDTO stockDTO){
        String response= productService.updateproductstock(productid,stockDTO);
        return ResponseEntity.ok().body(response);
    }
}
