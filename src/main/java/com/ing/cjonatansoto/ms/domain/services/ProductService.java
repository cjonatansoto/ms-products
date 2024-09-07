package com.ing.cjonatansoto.ms.domain.services;


import com.ing.cjonatansoto.ms.web.requests.ProductRequest;
import com.ing.cjonatansoto.ms.web.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<ProductResponse> findAll();

    Page<ProductResponse> findAllWithPaged(Pageable pageable);

    List<ProductResponse> findAllByNameContaining(String name);

    ProductResponse findById(Long id);

    ProductResponse save(ProductRequest productRequest);

    ProductResponse update(Long id, ProductRequest productRequest);

    void delete(Long id);
}
