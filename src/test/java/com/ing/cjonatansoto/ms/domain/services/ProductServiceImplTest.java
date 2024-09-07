package com.ing.cjonatansoto.ms.domain.services;

import com.ing.cjonatansoto.ms.domain.entities.Product;
import com.ing.cjonatansoto.ms.domain.services.impl.ProductServiceImpl;
import com.ing.cjonatansoto.ms.fixture.ProductFixture;
import com.ing.cjonatansoto.ms.infrastructure.exceptions.EnumError;
import com.ing.cjonatansoto.ms.infrastructure.exceptions.SimpleException;
import com.ing.cjonatansoto.ms.infrastructure.repositories.ProductRepository;
import com.ing.cjonatansoto.ms.web.mappers.ProductMapper;
import com.ing.cjonatansoto.ms.web.requests.ProductRequest;
import com.ing.cjonatansoto.ms.web.responses.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductRequest productRequest;
    private ProductResponse productResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = ProductFixture.createProduct();
        productRequest = ProductFixture.createProductRequest();
        productResponse = ProductFixture.createProductResponse();
    }

    @Test
    void findAll_ShouldReturnAllProducts() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
        when(productMapper.entityToResponse(product)).thenReturn(productResponse);

        List<ProductResponse> result = productService.findAll();

        assertEquals(1, result.size());
        assertEquals(productResponse, result.get(0));
        verify(productRepository).findAll();
    }

    @Test
    void findAll_ShouldThrowException_WhenNoProductsFound() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
        Exception exception = assertThrows(SimpleException.class, () -> productService.findAll());
        assertEquals(EnumError.NO_CONTENT.getCode(), ((SimpleException) exception).getCode());
    }

    @Test
    void findAllWithPaged_ShouldReturnPagedProducts() {
        Pageable pageable = mock(Pageable.class);
        Page<Product> page = new PageImpl<>(Collections.singletonList(product));
        when(productRepository.findAll(pageable)).thenReturn(page);
        when(productMapper.entityToResponse(product)).thenReturn(productResponse);

        Page<ProductResponse> result = productService.findAllWithPaged(pageable);

        assertEquals(1, result.getTotalElements());
        assertEquals(productResponse, result.getContent().get(0));
        verify(productRepository).findAll(pageable);
    }

    @Test
    void findAllWithPaged_ShouldThrowException_WhenNoProductsFound() {
        Pageable pageable = mock(Pageable.class);
        Page<Product> page = new PageImpl<>(Collections.emptyList());
        when(productRepository.findAll(pageable)).thenReturn(page);
        Exception exception = assertThrows(SimpleException.class, () -> productService.findAllWithPaged(pageable));
        assertEquals(EnumError.NO_CONTENT.getCode(), ((SimpleException) exception).getCode());
    }

    @Test
    void findAllByNameContaining_ShouldReturnProducts() {
        String name = "Iphone";
        when(productRepository.findByNameContaining(name)).thenReturn(Collections.singletonList(product));
        when(productMapper.entityToResponse(product)).thenReturn(productResponse);
        List<ProductResponse> result = productService.findAllByNameContaining(name);
        assertEquals(1, result.size());
        assertEquals(productResponse, result.get(0));
        assertEquals("Iphone 14 Pro Max", result.get(0).getName());
        verify(productRepository).findByNameContaining(name);
    }

    @Test
    void findAllByNameContaining_ShouldThrowException_WhenNoProductsFound() {
        String name = "Iphone";
        when(productRepository.findByNameContaining(name)).thenReturn(Collections.emptyList());
        Exception exception = assertThrows(SimpleException.class, () -> productService.findAllByNameContaining(name));
        assertEquals(EnumError.NO_CONTENT.getCode(), ((SimpleException) exception).getCode());
    }

    @Test
    void findById_ShouldReturnProduct_WhenFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(productMapper.entityToResponse(product)).thenReturn(productResponse);

        ProductResponse result = productService.findById(1L);

        assertEquals(productResponse, result);
        verify(productRepository).findById(1L);
    }

    @Test
    void findById_ShouldThrowException_WhenNotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(SimpleException.class, () -> productService.findById(1L));
        assertEquals(EnumError.NO_CONTENT.getCode(), ((SimpleException) exception).getCode());
    }

    @Test
    void save_ShouldReturnSavedProduct() {
        when(productMapper.requestToEntity(productRequest)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(productMapper.entityToResponse(product)).thenReturn(productResponse);
        ProductResponse result = productService.save(productRequest);
        assertEquals(productResponse, result);
        verify(productRepository).save(product);
    }

    @Test
    void update_ShouldReturnUpdatedProduct() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(productMapper.entityToResponse(product)).thenReturn(productResponse);
        ProductResponse result = productService.update(1L, productRequest);
        assertEquals(productResponse, result);
        assertEquals("Iphone 14 Pro Max", product.getName());
        verify(productRepository).saveAndFlush(product);
    }

    @Test
    void update_ShouldThrowException_WhenNotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(SimpleException.class, () -> productService.update(1L, productRequest));
        assertEquals(EnumError.NO_CONTENT.getCode(), ((SimpleException) exception).getCode());
    }

    @Test
    void delete_ShouldDeleteProduct() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        productService.delete(1L);
        verify(productRepository).delete(product);
    }

    @Test
    void delete_ShouldThrowException_WhenNotFound() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        Exception exception = assertThrows(SimpleException.class, () -> productService.delete(1L));
        assertEquals(EnumError.NO_CONTENT.getCode(), ((SimpleException) exception).getCode());
    }

}
