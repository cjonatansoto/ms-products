package com.ing.cjonatansoto.ms.mappers;

import com.ing.cjonatansoto.ms.entities.Product;
import com.ing.cjonatansoto.ms.web.responses.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "dd-MM-yyyy HH:mm:ss")
    ProductResponse entityToResponse(Product product);

}
