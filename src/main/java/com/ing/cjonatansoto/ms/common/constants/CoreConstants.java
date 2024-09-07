package com.ing.cjonatansoto.ms.common.constants;

public class CoreConstants {
    public static final String DATE_FORMAT_DDMMYYYY_HHMMSS = "dd-MM-yyyy HH:mm:ss";
    public static final String CLAUSE_SOFT_DELETED = "deleted_at IS NULL";
    private CoreConstants() {
        throw new UnsupportedOperationException("Esta clase no puede ser instanciada");
    }
}
