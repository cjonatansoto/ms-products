package com.ing.cjonatansoto.ms.web.constants;

public class ApiPaths {

    public static final String BASE_PATH = "/api";
    public static final String PATH_PRODUCTS = BASE_PATH + "/products";
    public static final String PATH_WITH_PAGINATION = "/with-paged";
    public static final String PATH_ID = "/{id}";
    public static final String PATH_SEARCH_BY_NAME = "/search/name/{name}";

    private ApiPaths() {
        throw new UnsupportedOperationException("Esta clase no puede ser instanciada");
    }
}
