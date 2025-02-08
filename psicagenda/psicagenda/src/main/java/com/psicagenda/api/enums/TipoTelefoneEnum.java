package com.psicagenda.api.enums;

public enum TipoTelefoneEnum {

    CELULAR("CELULAR"),
    COMERCIAL("COMERCIAL"),
    RESIDENCIAL("RESIDENCIAL");

    private final String descricao;

    TipoTelefoneEnum(String descricao) {
        this.descricao = descricao;
    }
}
