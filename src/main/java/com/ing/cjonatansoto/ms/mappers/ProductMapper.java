package com.ing.cjonatansoto.ms.mappers;

import com.ing.cjonatansoto.ms.entities.Product;
import com.ing.cjonatansoto.ms.web.requests.ProductRequest;
import com.ing.cjonatansoto.ms.web.responses.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse entityToResponse(Product product);
    Product requestToEntity(ProductRequest productRequest);
}
