package com.psicagenda.api.mapper.representations;

import com.psicagenda.api.enums.TipoTelefoneEnum;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class TelefoneRepresentationInput {

    @NotBlank
    private String ddi;

    @NotBlank
    private String ddd;

    @NotBlank
    private String numero;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoTelefoneEnum tipoTelefone;

    private Boolean status;

    @Column(columnDefinition = "TEXT")
    private String descricao;
}
