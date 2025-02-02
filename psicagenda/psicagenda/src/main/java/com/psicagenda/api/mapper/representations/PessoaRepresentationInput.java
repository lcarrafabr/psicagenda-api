package com.psicagenda.api.mapper.representations;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class PessoaRepresentationInput {

    @NotBlank
    private String nome;

    @NotBlank
    private String tipoCLiente;
}
