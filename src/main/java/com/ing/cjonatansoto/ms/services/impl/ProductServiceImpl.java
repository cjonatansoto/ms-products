package com.ing.cjonatansoto.ms.services.impl;

import com.ing.cjonatansoto.ms.entities.Product;
import com.ing.cjonatansoto.ms.exception.EnumError;
import com.ing.cjonatansoto.ms.exception.SimpleException;
import com.ing.cjonatansoto.ms.mappers.ProductMapper;
import com.ing.cjonatansoto.ms.repositories.ProductRepository;
import com.ing.cjonatansoto.ms.services.ProductService;
import com.ing.cjonatansoto.ms.web.requests.ProductRequest;
import com.ing.cjonatansoto.ms.web.responses.ProductResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> findAll() {
        log.info("Iniciando la búsqueda de todos los productos.");
        List<ProductResponse> products = this.productRepository.findAll()
                .stream()
                .map(productMapper::entityToResponse)
                .toList();
        log.info("Se encontraron {} productos.", products.size());
        return products;
    }

    @Override
    public Page<ProductResponse> findAllWithPaged(Pageable pageable) {
        log.info("Iniciando la búsqueda de productos con paginación.");
        Page<ProductResponse> productsPage = this.productRepository.findAll(pageable)
                .map(productMapper::entityToResponse);
        log.info("Se encontraron {} productos (página: {}).", productsPage.getTotalElements(), pageable.getPageNumber());
        return productsPage;
    }

    @Override
    public List<ProductResponse> findAllByNameContaining(String name) {
        log.info("Buscando productos que contienen el nombre: {}", name);
        List<ProductResponse> products = this.productRepository.findByNameContaining(name)
                .stream()
                .map(productMapper::entityToResponse)
                .toList();
        log.info("Se encontraron {} productos que contienen '{}'.", products.size(), name);
        return products;
    }

    @Override
    public ProductResponse getById(Long id) {
        return this.productMapper.entityToResponse(this.findById(id));
    }

    @Transactional
    @Override
    public ProductResponse save(ProductRequest productRequest) {
        log.info("Iniciando el guardado de un nuevo producto: {}", productRequest);
        var product = this.productMapper.requestToEntity(productRequest);
        var savedProduct = this.productRepository.save(product);
        log.info("Producto guardado con éxito: {}", savedProduct.getId());
        return this.productMapper.entityToResponse(savedProduct);
    }

    @Transactional
    @Override
    public ProductResponse update(Long id, ProductRequest productRequest) {
        log.info("Iniciando la actualización del producto con ID: {}", id);
        var product = this.findById(id);
        product.setName(Objects.requireNonNullElse(productRequest.getName(), product.getName()));
        product.setPrice(Objects.requireNonNullElse(productRequest.getPrice(), product.getPrice()));
        this.productRepository.save(product);
        log.info("Producto con ID: {} actualizado exitosamente.", id);
        return this.productMapper.entityToResponse(product);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        log.info("Iniciando la eliminación del producto con ID: {}", id);
        var product = findById(id);
        this.productRepository.delete(product);
        log.info("Producto con ID: {} eliminado exitosamente.", id);
    }

    // Métodos privados

    private Product findById(Long id) {
        log.info("Buscando producto con ID: {}", id);
        return this.productRepository.findById(id)
                .orElseThrow(() -> new SimpleException(EnumError.NO_CONTENT, HttpStatus.NOT_FOUND.value()));
    }
}