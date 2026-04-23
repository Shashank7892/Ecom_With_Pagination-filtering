package com.EcommerceproductApiDessign.EcommerceProduct.dtohelpers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginatedResponse<T> {
    private List<T> results;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
