package com.ing.cjonatansoto.ms.web.controller;

import com.ing.cjonatansoto.ms.exception.EnumError;
import com.ing.cjonatansoto.ms.exception.SimpleException;
import com.ing.cjonatansoto.ms.services.ProductService;
import com.ing.cjonatansoto.ms.web.responses.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductController
 */
@RestController
@RequestMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(description = "Listado de productos")
    @ApiResponse(responseCode = "200", description = "Listado de productos")
    @ApiResponse(responseCode = "204", description = "No existen productos, listado vacio")
    @ApiResponse(responseCode = "500", description = "Error general")
    @GetMapping()
    public Page<ProductResponse> fetchPagedProducts(Pageable pageable){
        var products = this.productService.fetchPagedProducts(pageable);
        if(!products.hasContent()){
            throw new SimpleException(EnumError.NO_CONTENT, HttpStatus.NO_CONTENT.value());
        }
        return products;
    }

}
