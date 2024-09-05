package com.ing.cjonatansoto.ms.web.controller;

import com.ing.cjonatansoto.ms.exception.EnumError;
import com.ing.cjonatansoto.ms.exception.SimpleException;
import com.ing.cjonatansoto.ms.services.ProductService;
import com.ing.cjonatansoto.ms.web.requests.ProductRequest;
import com.ing.cjonatansoto.ms.web.responses.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProductController
 */
@RestController
@RequestMapping(path = "v1/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/with-paged")
    public ResponseEntity<Page<ProductResponse>> findAllWithPaged(Pageable pageable){
        var products = this.productService.findAllWithPaged(pageable);
        if(!products.hasContent()){
            throw new SimpleException(EnumError.NO_CONTENT, HttpStatus.NO_CONTENT.value());
        }
        return ResponseEntity.ok(products);
    }


    @GetMapping()
    public ResponseEntity<List<ProductResponse>> findAll(){
        var products = this.productService.findAll();
        if(products.isEmpty()){
            throw new SimpleException(EnumError.NO_CONTENT, HttpStatus.NO_CONTENT.value());
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/search-by-name/{name}")
    public ResponseEntity<List<ProductResponse>> findAllByNameContaining(@PathVariable String name){
        var products = this.productService.findAllByNameContaining(name);
        if(products.isEmpty()){
            throw new SimpleException(EnumError.NO_CONTENT, HttpStatus.NO_CONTENT.value());
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> findByid(@PathVariable Long id){
        var product = this.productService.getById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(this.productService.save(productRequest));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ProductResponse> update(@Valid @PathVariable Long id, @RequestBody ProductRequest request) {
        return ResponseEntity.ok(this.productService.update(id, request));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
