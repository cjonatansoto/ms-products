package com.ing.cjonatansoto.ms.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.warn("Validation failed for method argument. Errors: {}", extractErrorMessages(ex.getBindingResult()));
        return this.handleExceptionInternal(ex, extractErrorMessages(ex.getBindingResult()), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return this.handleExceptionInternal(ex, new SimpleException(EnumError.NO_RESOURCE_FOUND), headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("HTTP request method not supported: {}. Supported methods: {}", ex.getMethod(), ex.getSupportedHttpMethods());
        return this.handleExceptionInternal(ex, new SimpleException(EnumError.NO_SUPPORT), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.warn("Servlet request binding exception: {}", ex.getMessage());
        return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("HTTP message not readable: {}", ex.getMessage());
        return this.handleExceptionInternal(ex, new SimpleException(EnumError.INVALID_BODY), headers, status, request);
    }

    @ExceptionHandler({SimpleException.class})
    public ResponseEntity<Object> handleSimpleException(final SimpleException ex, final WebRequest webRequest) {
        log.warn("Handled controlled exception. Status: {}, Code: {}, Message: {}", ex.getStatus(), ex.getCode(), ex.getMessage());
        return this.doHandleSimpleException(ex, webRequest);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGeneralException(final Exception ex, final WebRequest webRequest) {
        log.error("Uncontrolled exception occurred: {}", ex.getMessage(), ex);
        return this.handleExceptionInternal(ex, new SimpleException(EnumError.DEFAULT), new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleArgumentTypeMismatchException(final Exception ex, final WebRequest webRequest) {
        log.warn("Argument type mismatch exception: {}", ex.getMessage());
        return this.handleExceptionInternal(ex, new SimpleException(EnumError.INVALID_ARGS), new HttpHeaders(),
                HttpStatus.BAD_REQUEST, webRequest);
    }

    /*
    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<Object> handleNoResourceFoundException(final Exception ex, final WebRequest webRequest) {
        log.warn("Argument type mismatch exception: {}", ex.getMessage());
        return this.handleExceptionInternal(ex, new SimpleException(EnumError.INVALID_ARGS), new HttpHeaders(),
                HttpStatus.BAD_REQUEST, webRequest);
    }*/

    private ResponseEntity<Object> doHandleSimpleException(final SimpleException ex, final WebRequest webRequest) {
        log.info("Handling simple exception: {}, status: {}", ex.getMessage(), ex.getStatus());
        return this.handleExceptionInternal(ex, ex, new HttpHeaders(), HttpStatus.valueOf(ex.getStatus()), webRequest);
    }

    private Map<String, String> extractErrorMessages(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (existing, replacement) -> existing
                ));
    }
}
