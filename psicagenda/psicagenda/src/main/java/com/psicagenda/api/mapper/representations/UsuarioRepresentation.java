package com.psicagenda.api.mapper.representations;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioRepresentation {

    private String codigoUsuario;
    private String email;
    private PessoaRepesentation pessoa;
}
