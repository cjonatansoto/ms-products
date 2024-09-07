package com.ing.cjonatansoto.ms.web.mappers;

import com.ing.cjonatansoto.ms.web.requests.ProductRequest;
import com.ing.cjonatansoto.ms.web.responses.ProductResponse;
import com.ing.cjonatansoto.ms.domain.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse entityToResponse(Product product);
    Product requestToEntity(ProductRequest productRequest);
}
