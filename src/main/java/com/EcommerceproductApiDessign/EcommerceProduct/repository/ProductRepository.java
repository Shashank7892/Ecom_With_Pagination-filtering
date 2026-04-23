package com.EcommerceproductApiDessign.EcommerceProduct.repository;

import com.EcommerceproductApiDessign.EcommerceProduct.entity.Productentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Productentity,Long>, JpaSpecificationExecutor<Productentity> {
    Productentity findByProductname(String productName);
}
