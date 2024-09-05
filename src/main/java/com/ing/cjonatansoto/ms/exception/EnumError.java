package com.ing.cjonatansoto.ms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumError {
    DEFAULT("E_GENERIC_500", "Error genérico: Se ha producido un error inesperado en el sistema."),
    REST_CLIENT("E_SERVICE_001", "Error al consumir servicio REST: No se pudo establecer la conexión con el servidor o el servicio no responde adecuadamente."),
    INVALID_ARGS("E_INVALID_ARGS_002", "Argumentos inválidos: Los parámetros proporcionados no cumplen con los requisitos del servicio."),
    NOT_ALLOWED("E_NOT_ALLOWED_003", "Operación no permitida: La acción solicitada no está permitida según las reglas de negocio."),
    INVALID_BODY("E_INVALID_BODY_004", "Cuerpo de llamada inválido: El formato o la estructura del cuerpo de la solicitud es incorrecto."),
    NO_CONTENT("E_NO_CONTENT_005", "Sin datos disponibles: No se encontraron registros que coincidan con los criterios solicitados.");
    private String code;
    private String message;
}
