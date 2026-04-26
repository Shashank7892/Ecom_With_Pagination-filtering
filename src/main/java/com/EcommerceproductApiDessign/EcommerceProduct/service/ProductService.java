package com.EcommerceproductApiDessign.EcommerceProduct.service;

import com.EcommerceproductApiDessign.EcommerceProduct.dtohelpers.*;
import com.EcommerceproductApiDessign.EcommerceProduct.entity.Productentity;
import com.EcommerceproductApiDessign.EcommerceProduct.repository.ProductRepository;
import com.EcommerceproductApiDessign.EcommerceProduct.specifications.Productspecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public String addproduct(AddProductDTO productDTO) {

        Productentity productentity = productRepository.findByProductname(productDTO.getProductname());
        if(productentity==null) {
            Productentity productentity1=Productentity.builder()
                    .productname(productDTO.getProductname())
                    .description(productDTO.getDescription())
                    .price(productDTO.getPrice())
                    .category(productDTO.getCategory())
                    .brand(productDTO.getBrand())
                    .imageurl(productDTO.getImageurl())
                    .stockQuantity(productDTO.getStockQuantity())
                    .totalbuy(0L)
                    .ratings(0.0)
                    .inStock(productDTO.getStockQuantity()>0)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            productRepository.save(productentity1);
        }
        else{
            productentity.setStockQuantity(productentity.getStockQuantity()+productDTO.getStockQuantity());
            productentity.setInStock(productentity.getStockQuantity()>0);
            productentity.setUpdatedAt(LocalDateTime.now());
            productRepository.save(productentity);
        }
        return "Added Successfully";
    }

    public PaginatedResponse<ProductResponseDTO> getallproducts(String category,
                                                           String brand,
                                                           Double minPrice,
                                                           Double maxPrice,
                                                           int page,
                                                           int size,
                                                           String sortBy,
                                                           String direction){

        size = Math.min(size, 50);
        Sort sort=direction.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(page,size,sort);
        Specification<Productentity> spec=Productspecification.filterProducts(category,brand,minPrice,maxPrice);

        Page<Productentity> productPage = productRepository.findAll(spec, pageable);

        Page<ProductResponseDTO> dtopage=productPage.map(product->
                ProductResponseDTO.builder()
                        .productname(product.getProductname())
                        .description(product.getDescription())
                        .ratings(product.getRatings())
                        .totalbuy(product.getTotalbuy())
                        .price(product.getPrice())
                        .imageurl(product.getImageurl())
                        .stockQuantity(product.getStockQuantity())
                        .category(product.getCategory())
                        .brand(product.getBrand())
                        .build());

        return PaginatedResponse.<ProductResponseDTO>builder()
                .results(dtopage.getContent())
                .page(dtopage.getNumber())
                .size(dtopage.getSize())
                .totalElements(dtopage.getTotalElements())
                .totalPages(dtopage.getTotalPages())
                .build();
    }


    public ProductResponseDTO getByProductid(Long productid){
        Productentity product=productRepository.findById(productid).orElse(null);
        if(product==null) {
            throw new RuntimeException("product not found");
        }
        return ProductResponseDTO.builder()
                .productname(product.getProductname())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .imageurl(product.getImageurl())
                .brand(product.getBrand())
                .totalbuy(product.getTotalbuy())
                .ratings(product.getRatings())
                .stockQuantity(product.getStockQuantity())
                .build();
    }

    public String deleteByproductid(Long productid){
        Productentity product=productRepository.findById(productid).orElse(null);
        if(product==null) {
            throw new RuntimeException("product not found");
        }
        productRepository.deleteById(productid);
        return "Deleted Successfully";
    }

    public String updateProductprice(Long productid, PriceUpdateDTO priceUpdateDTO){
        Productentity product=productRepository.findById(productid).orElse(null);
        if(product==null) {
            throw new RuntimeException("product not found");
        }
        product.setPrice(priceUpdateDTO.getPrice());
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);

        return "Updated Successfully";
    }

    public String updateproductstock(Long productid, UpdateStockDTO updateStockDTO){
        Productentity product=productRepository.findById(productid).orElse(null);
        if(product==null) {
            throw new RuntimeException("product not found");
        }
        product.setStockQuantity(product.getStockQuantity()+updateStockDTO.getQuantity());
        product.setInStock(product.getStockQuantity()>0);
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
        return "Product Stock Updated Successfully";
    }

}
