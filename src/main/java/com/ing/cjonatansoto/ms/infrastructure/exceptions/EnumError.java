package com.ing.cjonatansoto.ms.infrastructure.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumError {
    DEFAULT("E_UNKNOWN_ERROR", "Error inesperado: Se ha producido un problema en el sistema. Por favor, inténtelo de nuevo más tarde."),
    INVALID_ARGS("E_INVALID_PARAMETERS", "Parámetros inválidos: Los argumentos proporcionados no cumplen con las especificaciones requeridas por el servicio."),
    INVALID_BODY("E_INVALID_REQUEST_BODY", "Cuerpo de solicitud inválido: El formato o la estructura del cuerpo de la solicitud son incorrectos."),
    NO_CONTENT("E_NO_RECORDS_FOUND", "Sin resultados: No se encontraron registros que coincidan con los criterios especificados."),
    NO_SUPPORT("E_METHOD_NOT_SUPPORTED", "Método no admitido: La acción solicitada no está disponible en este contexto."),
    NO_RESOURCE_FOUND("E_RESOURCE_NOT_FOUND", "Recurso no disponible: No se encontró el recurso solicitado en el sistema.");
    private String code;
    private String message;
}
