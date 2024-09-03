package com.ing.cjonatansoto.ms.services;

import com.ing.cjonatansoto.ms.web.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductResponse> fetchPagedProducts (Pageable pageable);
}
