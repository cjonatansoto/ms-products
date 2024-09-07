package com.ing.cjonatansoto.ms.infrastructure.exceptions;

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
        log.warn("La validacion de argumentos del metodo fallo. Errores: {}", extractErrorMessages(ex.getBindingResult()));
        return this.handleExceptionInternal(ex, extractErrorMessages(ex.getBindingResult()), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("No se encontro el recurso solicitado: {}", ex.getMessage());
        return this.handleExceptionInternal(ex, new SimpleException(EnumError.NO_RESOURCE_FOUND), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("Metodo de solicitud HTTP no soportado: {}. Métodos soportados: {}", ex.getMethod(), ex.getSupportedHttpMethods());
        return this.handleExceptionInternal(ex, new SimpleException(EnumError.NO_SUPPORT), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.warn("Excepcion de enlace de solicitud del servlet: {}", ex.getMessage());
        return this.handleExceptionInternal(ex, (Object) null, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("Mensaje HTTP no legible: {}", ex.getMessage());
        return this.handleExceptionInternal(ex, new SimpleException(EnumError.INVALID_BODY), headers, status, request);
    }

    @ExceptionHandler({SimpleException.class})
    public ResponseEntity<Object> handleSimpleException(final SimpleException ex, final WebRequest webRequest) {
        log.warn("Excepcion controlada. Estado: {}, Codigo: {}, Mensaje: {}", ex.getStatus(), ex.getCode(), ex.getMessage());
        return this.doHandleSimpleException(ex, webRequest);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGeneralException(final Exception ex, final WebRequest webRequest) {
        log.error("Ocurrio una excepcion no controlada: {}", ex.getMessage(), ex);
        return this.handleExceptionInternal(ex, new SimpleException(EnumError.DEFAULT), new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleArgumentTypeMismatchException(final Exception ex, final WebRequest webRequest) {
        log.warn("Excepcion de desajuste de tipo de argumento: {}", ex.getMessage());
        return this.handleExceptionInternal(ex, new SimpleException(EnumError.INVALID_ARGS), new HttpHeaders(),
                HttpStatus.BAD_REQUEST, webRequest);
    }

    private ResponseEntity<Object> doHandleSimpleException(final SimpleException ex, final WebRequest webRequest) {
        log.info("Manejando excepcion simple: {}, estado: {}", ex.getMessage(), ex.getStatus());
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
