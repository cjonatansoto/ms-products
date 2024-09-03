package com.ing.cjonatansoto.ms.services.impl;

import com.ing.cjonatansoto.ms.repositories.ProductRepository;
import com.ing.cjonatansoto.ms.services.ProductService;
import com.ing.cjonatansoto.ms.web.vm.ProductViewModel;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ConversionService conversionService;

    @Override
    public Page<ProductViewModel> fetchPagedProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable)
                .map(product -> conversionService.convert(product, ProductViewModel.class));
    }
}
