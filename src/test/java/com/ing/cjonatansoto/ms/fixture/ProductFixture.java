package com.ing.cjonatansoto.ms.fixture;


import com.ing.cjonatansoto.ms.domain.entities.Product;
import com.ing.cjonatansoto.ms.web.requests.ProductRequest;
import com.ing.cjonatansoto.ms.web.responses.ProductResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * ClienteFixture
 */
public class ProductFixture {

    public static List<Product> createProductList() {
        return List.of(Product.builder()
                .id(1L)
                .name("Iphone 14 Pro Max")
                .price(new BigDecimal(1200))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .deletedAt(null)
                .build());
    }

    public static List<Product> createEmptyProductList() {
        return List.of();
    }

    public static Product createProduct() {
        return Product.builder()
                .id(1L)
                .name("Iphone 14 Pro Max")
                .price(new BigDecimal(1200))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .deletedAt(null)
                .build();
    }

    public static ProductRequest createProductRequest() {
        return ProductRequest.builder()
                .name("Iphone 14 Pro Max")
                .price(new BigDecimal(1200))
                .build();
    }

    public static List<ProductResponse> createProductResponseList() {

        var product01 = ProductResponse.builder()
                .id(1L)
                .name("Iphone 14 Pro Max")
                .price(new BigDecimal(1200))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        var product02 = ProductResponse.builder()
                .id(2L)
                .name("Iphone 14 Pro")
                .price(new BigDecimal(1000))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        var product03 = ProductResponse.builder()
                .id(3L)
                .name("Iphone 15 Pro Max")
                .price(new BigDecimal(1600))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        var product04 = ProductResponse.builder()
                .id(4L)
                .name("Mac Pro M1 2023")
                .price(new BigDecimal(2500))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return List.of(product01, product02, product03, product04);
    }

    public static ProductResponse createProductResponse() {
        return ProductResponse.builder()
                .id(1L)
                .name("Iphone 14 Pro Max")
                .price(new BigDecimal(1200))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
