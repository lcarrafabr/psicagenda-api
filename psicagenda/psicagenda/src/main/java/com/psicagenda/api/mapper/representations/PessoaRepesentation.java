package com.psicagenda.api.mapper.representations;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class PessoaRepesentation {

    private String codigoPessoa;
    private String nomePessoa;
    private LocalDateTime dataCadastro;
    private Boolean status;
}
