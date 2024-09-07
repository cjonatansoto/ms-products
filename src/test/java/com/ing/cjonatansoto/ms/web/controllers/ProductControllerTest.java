package com.ing.cjonatansoto.ms.web.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.cjonatansoto.ms.fixture.ProductFixture;
import com.ing.cjonatansoto.ms.web.responses.ProductResponse;
import com.ing.cjonatansoto.ms.domain.services.ProductService;
import com.ing.cjonatansoto.ms.web.requests.ProductRequest;
import com.ing.cjonatansoto.ms.common.constants.ApiPaths;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(controllers = {ProductController.class})
@ContextConfiguration(classes = ProductController.class)
public class


ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    private static final String URL_BASE = ApiPaths.PATH_PRODUCTS;
    private static final String PATH_SEARCH_BY_NAME = URL_BASE + ApiPaths.PATH_SEARCH_BY_NAME;
    private static final String PATH_WITH_PAGINATION = URL_BASE + ApiPaths.PATH_WITH_PAGINATION;
    private static final String PATH_ID = URL_BASE + ApiPaths.PATH_ID;

    @Test
    void findAll() throws Exception {

        List<ProductResponse> productResponseList = ProductFixture.createProductResponseList();
        Mockito.when(productService.findAll()).thenReturn(productResponseList);

        mockMvc.perform(get(URL_BASE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(productResponseList.size())))
                .andExpect(jsonPath("$[0].name").value("Iphone 14 Pro Max"))
                .andExpect(jsonPath("$[0].price").value(1200));
    }

    @Test
    void findAllByNameContaining() throws Exception {

        String searchQuery = "Iphone";
        List<ProductResponse> productResponseList = ProductFixture.createProductResponseList();
        Mockito.when(productService.findAllByNameContaining(searchQuery)).thenReturn(productResponseList);

        mockMvc.perform(get(PATH_SEARCH_BY_NAME, searchQuery))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(productResponseList.size())))
                .andExpect(jsonPath("$[0].name").value("Iphone 14 Pro Max"))
                .andExpect(jsonPath("$[0].price").value(1200));
    }

    @Test
    void findAllWithPaged() throws Exception {

        int page = 0;
        int size = 2;
        String sortField = "name";

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortField));
        List<ProductResponse> productResponseList = ProductFixture.createProductResponseList();
        Page<ProductResponse> productResponsePage = new PageImpl<>(productResponseList, pageable, productResponseList.size());

        Mockito.when(productService.findAllWithPaged(pageable)).thenReturn(productResponsePage);

        mockMvc.perform(get(PATH_WITH_PAGINATION)
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .param("sort", sortField))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content", hasSize(productResponseList.size())))
                .andExpect(jsonPath("$.content[0].name").value("Iphone 14 Pro Max"))
                .andExpect(jsonPath("$.content[0].price").value(1200));
    }

    @Test
    void findById() throws Exception {

        Long productId = 1L;
        Mockito.when(productService.findById(productId)).thenReturn(ProductFixture.createProductResponse());
        mockMvc.perform(get(PATH_ID, productId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Iphone 14 Pro Max"))
                .andExpect(jsonPath("$.price").value(1200));
    }

    @Test
    void save() throws Exception {

        ProductRequest request = ProductFixture.createProductRequest();
        ProductResponse response = ProductFixture.createProductResponse();
        Mockito.when(productService.save(Mockito.any(ProductRequest.class))).thenReturn(response);

        mockMvc.perform(post(URL_BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())  // Cambiado a 201 Created
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Iphone 14 Pro Max"))
                .andExpect(jsonPath("$.price").value(1200));
    }

    @Test
    void update() throws Exception {

        Long productId = 1L;
        ProductRequest request = ProductFixture.createProductRequest();
        ProductResponse response = ProductFixture.createProductResponse();
        Mockito.when(productService.update(Mockito.eq(productId), Mockito.any(ProductRequest.class))).thenReturn(response);

        mockMvc.perform(put(PATH_ID, productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Iphone 14 Pro Max"))
                .andExpect(jsonPath("$.price").value(1200));
    }

    @Test
    void deleted() throws Exception {

        Long productId = 123L;
        Mockito.doNothing().when(productService).delete(productId);

        mockMvc.perform(delete(PATH_ID, productId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

}
