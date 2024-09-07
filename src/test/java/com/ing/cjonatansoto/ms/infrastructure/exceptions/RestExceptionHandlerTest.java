package com.ing.cjonatansoto.ms.infrastructure.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.WebRequest;

class CustomExceptionHandlerTest {

    @InjectMocks
    private RestExceptionHandler restExceptionHandler;

    @Mock
    private WebRequest webRequest;

    private static final HttpHeaders HTTP_HEADERS = HttpHeaders.EMPTY;
    private static final HttpStatus INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final HttpStatus METHOD_NOT_ALLOWED = HttpStatus.METHOD_NOT_ALLOWED;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnMethodNotAllowedResponseWhenHttpRequestMethodNotSupported() {
        var exception = Mockito.mock(HttpRequestMethodNotSupportedException.class);
        var response = restExceptionHandler.handleHttpRequestMethodNotSupported(exception,
                HTTP_HEADERS, METHOD_NOT_ALLOWED, webRequest);
        assertResponse(response, METHOD_NOT_ALLOWED);
    }

    @Test
    void shouldReturnInternalServerErrorWhenServletRequestBindingExceptionOccurs() {
        var exception = Mockito.mock(ServletRequestBindingException.class);
        var response = restExceptionHandler.handleServletRequestBindingException(exception,
                HTTP_HEADERS, INTERNAL_SERVER_ERROR, webRequest);
        assertResponse(response, INTERNAL_SERVER_ERROR);
    }

    @Test
    void shouldReturnInternalServerErrorWhenHttpMessageNotReadableExceptionOccurs() {
        var exception = Mockito.mock(HttpMessageNotReadableException.class);
        var response = restExceptionHandler.handleHttpMessageNotReadable(exception,
                HTTP_HEADERS, INTERNAL_SERVER_ERROR, webRequest);
        assertResponse(response, INTERNAL_SERVER_ERROR);
    }

    @Test
    void shouldReturnInternalServerErrorForSimpleException() {
        var exception = new SimpleException(EnumError.DEFAULT);
        var response = restExceptionHandler.handleSimpleException(exception, webRequest);
        assertResponse(response, INTERNAL_SERVER_ERROR);
    }

    @Test
    void shouldReturnInternalServerErrorForGeneralException() {
        var exception = Mockito.mock(HttpMessageNotReadableException.class);
        var response = restExceptionHandler.handleGeneralException(exception, webRequest);
        assertResponse(response, INTERNAL_SERVER_ERROR);
        Assertions.assertInstanceOf(SimpleException.class, response.getBody());
    }

    private void assertResponse(ResponseEntity<Object> response, HttpStatus expectedStatus) {
        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedStatus, response.getStatusCode());
    }
}
