package com.ing.cjonatansoto.ms.services.impl;

import com.ing.cjonatansoto.ms.mappers.ProductMapper;
import com.ing.cjonatansoto.ms.repositories.ProductRepository;
import com.ing.cjonatansoto.ms.services.ProductService;
import com.ing.cjonatansoto.ms.web.responses.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<ProductResponse> fetchPagedProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable)
                .map(productMapper::entityToResponse);
    }
}
