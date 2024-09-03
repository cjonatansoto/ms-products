package com.ing.cjonatansoto.ms.services;

import com.ing.cjonatansoto.ms.web.vm.ProductViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductViewModel> fetchPagedProducts (Pageable pageable);
}
