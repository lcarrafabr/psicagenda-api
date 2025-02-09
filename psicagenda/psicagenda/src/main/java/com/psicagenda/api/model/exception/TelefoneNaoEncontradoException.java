package com.psicagenda.api.model.exception;

import com.psicagenda.api.exception.EntidadeNaoEncontradaException;

public class TelefoneNaoEncontradoException extends EntidadeNaoEncontradaException {

    public static final String NAO_EXISTE_CADASTRO_COM_ID = "Não existe cadastro de banco com o código %s";

    public TelefoneNaoEncontradoException(String telefoneId) {
        super(String.format(NAO_EXISTE_CADASTRO_COM_ID, telefoneId));
    }
}
