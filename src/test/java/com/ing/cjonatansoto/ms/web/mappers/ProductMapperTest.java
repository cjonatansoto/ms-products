package com.ing.cjonatansoto.ms.web.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ing.cjonatansoto.ms.domain.entities.Product;
import com.ing.cjonatansoto.ms.fixture.ProductFixture;
import com.ing.cjonatansoto.ms.web.requests.ProductRequest;
import com.ing.cjonatansoto.ms.web.responses.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class ProductMapperTest {

    private ProductMapper productMapper;

    @BeforeEach
    public void setUp() {
        productMapper = Mappers.getMapper(ProductMapper.class);
    }

    @Test
    public void testEntityToResponse() {

        Product product = ProductFixture.createProduct();

        ProductResponse productResponse = productMapper.entityToResponse(product);

        assertEquals(product.getId(), productResponse.getId());
        assertEquals(product.getName(), productResponse.getName());
        assertEquals(product.getPrice(), productResponse.getPrice());
    }

    @Test
    public void testRequestToEntity() {

        ProductRequest productRequest =  ProductFixture.createProductRequest();

        Product product = productMapper.requestToEntity(productRequest);
        assertEquals(productRequest.getName(), product.getName());
        assertEquals(productRequest.getPrice(), product.getPrice());
    }
}
