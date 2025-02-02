package com.psicagenda.api.mapper.representations;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CadastroRepresentationInput {

    @NotBlank
    private String nomePessoa;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;
}
