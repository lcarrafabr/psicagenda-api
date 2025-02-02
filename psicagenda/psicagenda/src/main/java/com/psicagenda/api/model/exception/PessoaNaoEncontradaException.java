package com.psicagenda.api.model.exception;

import com.psicagenda.api.exception.EntidadeNaoEncontradaException;

public class PessoaNaoEncontradaException extends EntidadeNaoEncontradaException {

    public static final String NAO_EXISTE_CADASTRO_COM_ID = "Não existe cadastro de banco com o código %s";

    public PessoaNaoEncontradaException(String message) {
        super(String.format(NAO_EXISTE_CADASTRO_COM_ID, message));
    }
}
