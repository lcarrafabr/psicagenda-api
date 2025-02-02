package com.psicagenda.api.exception.entidadeexception;

import com.psicagenda.api.exception.NegocioException;

public class EntidadeEmUsoException extends NegocioException {

    public EntidadeEmUsoException(String message) {
        super(message);
    }
}
