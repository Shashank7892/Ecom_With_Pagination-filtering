package com.EcommerceproductApiDessign.EcommerceProduct.specifications;

import com.EcommerceproductApiDessign.EcommerceProduct.entity.Productentity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class Productspecification {

    public static Specification<Productentity> filterProducts(String category,
                                                              String brand,
                                                              Double minprice,
                                                              Double maxprice) {
        return (root,query,cb)->{
            List<Predicate> predicates = new ArrayList<>();
            if (category != null && !category.isBlank()) {
                predicates.add(cb.equal(cb.lower(root.get("category")),category.toLowerCase()));
            }

            if (brand != null && !brand.isBlank()) {
                predicates.add(cb.equal(root.get("brand"),brand.toLowerCase()));
            }

            if (minprice != null && !minprice.isNaN()) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minprice));
            }
            if (maxprice != null && !maxprice.isNaN()) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxprice));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
