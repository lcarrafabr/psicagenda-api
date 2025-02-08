package com.psicagenda.api.mapper.representations;

import com.psicagenda.api.enums.TipoTelefoneEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TelefoneRepresentation {

    private String codigoTelefone;
    private String ddi;
    private String ddd;
    private String numero;
    private String tipoTelefone;
    private Boolean status;
    private String descricao;
    private PessoaIdRRepresentation pessoa;
}
