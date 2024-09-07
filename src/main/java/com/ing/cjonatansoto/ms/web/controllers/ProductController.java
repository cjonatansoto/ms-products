package com.ing.cjonatansoto.ms.web.controllers;

import com.ing.cjonatansoto.ms.web.requests.ProductRequest;
import com.ing.cjonatansoto.ms.web.responses.ProductResponse;
import com.ing.cjonatansoto.ms.domain.services.ProductService;
import com.ing.cjonatansoto.ms.common.constants.ApiPaths;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProductController
 */
@RestController
@RequestMapping(path = ApiPaths.PATH_PRODUCTS, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(description = "Obtiene una lista de productos paginada.", tags = {"ProductController"})
    @ApiResponse(responseCode = "200", description = "Lista de productos paginada obtenida exitosamente.")
    @ApiResponse(responseCode = "204", description = "No se encontraron productos; la lista está vacía.")
    @ApiResponse(responseCode = "500", description = "Se produjo un error inesperado al procesar la solicitud.")
    @GetMapping(value = ApiPaths.PATH_WITH_PAGINATION)
    public ResponseEntity<Page<ProductResponse>> findAllWithPaged(Pageable pageable) {
        var products = this.productService.findAllWithPaged(pageable);
        return ResponseEntity.ok(products);
    }

    @Operation(description = "Obtiene una lista completa de todos los productos.", tags = {"ProductController"})
    @ApiResponse(responseCode = "200", description = "Lista completa de todos los productos obtenida exitosamente.")
    @ApiResponse(responseCode = "204", description = "No se encontraron productos; la lista está vacía.")
    @ApiResponse(responseCode = "500", description = "Se produjo un error inesperado al procesar la solicitud.")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        var products = this.productService.findAll();
        return ResponseEntity.ok(products);
    }

    @Operation(description = "Busca productos por nombre. Devuelve una lista de productos que contienen el nombre especificado.", tags = {"ProductController"})
    @ApiResponse(responseCode = "200", description = "Lista de productos que coinciden con el criterio de búsqueda obtenida exitosamente.")
    @ApiResponse(responseCode = "204", description = "No se encontraron productos que coincidan con el criterio de búsqueda; la lista está vacía.")
    @ApiResponse(responseCode = "500", description = "Se produjo un error inesperado al procesar la solicitud.")
    @GetMapping(value = ApiPaths.PATH_SEARCH_BY_NAME)
    public ResponseEntity<List<ProductResponse>> findAllByNameContaining(@PathVariable String name) {
        var products = this.productService.findAllByNameContaining(name);
        return ResponseEntity.ok(products);
    }

    @Operation(description = "Obtiene un producto específico por su ID.", tags = {"ProductController"})
    @ApiResponse(responseCode = "200", description = "Información del producto obtenida exitosamente.")
    @ApiResponse(responseCode = "400", description = "El ID proporcionado es inválido.")
    @ApiResponse(responseCode = "404", description = "El producto con el ID especificado no fue encontrado.")
    @ApiResponse(responseCode = "500", description = "Se produjo un error inesperado al procesar la solicitud.")
    @GetMapping(value = ApiPaths.PATH_ID)
    public ResponseEntity<ProductResponse> findByid(@PathVariable Long id) {
        var product = this.productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @Operation(description = "Crea un nuevo producto con los detalles proporcionados.", tags = {"ProductController"})
    @ApiResponse(responseCode = "200", description = "El producto fue creado exitosamente.")
    @ApiResponse(responseCode = "400", description = "El cuerpo de la solicitud contiene datos inválidos.")
    @ApiResponse(responseCode = "500", description = "Se produjo un error inesperado al procesar la solicitud.")
    @PostMapping
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(this.productService.save(productRequest));
    }

    @Operation(description = "Actualiza los detalles de un producto existente identificado por su ID.", tags = {"ProductController"})
    @ApiResponse(responseCode = "200", description = "El producto fue actualizado exitosamente.")
    @ApiResponse(responseCode = "400", description = "El ID proporcionado o el cuerpo de la solicitud contienen datos inválidos.")
    @ApiResponse(responseCode = "404", description = "El producto con el ID especificado no fue encontrado.")
    @ApiResponse(responseCode = "500", description = "Se produjo un error inesperado al procesar la solicitud.")
    @PutMapping(path = ApiPaths.PATH_ID)
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(this.productService.update(id, request));
    }

    @Operation(description = "Elimina lógicamente un producto por su ID.", tags = {"ProductController"})
    @ApiResponse(responseCode = "200", description = "El producto fue eliminado exitosamente.")
    @ApiResponse(responseCode = "404", description = "El producto con el ID especificado no fue encontrado.")
    @ApiResponse(responseCode = "500", description = "Se produjo un error inesperado al procesar la solicitud.")
    @DeleteMapping(path = ApiPaths.PATH_ID)
    public ResponseEntity<Void> deleted(@PathVariable Long id) {
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
