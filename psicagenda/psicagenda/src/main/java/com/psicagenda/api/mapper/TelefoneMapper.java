package com.psicagenda.api.mapper;

import com.psicagenda.api.mapper.representations.PessoaIdRRepresentation;
import com.psicagenda.api.mapper.representations.TelefoneRepresentation;
import com.psicagenda.api.model.Telefones;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TelefoneMapper {

    public TelefoneRepresentation toTelefoneRepresentation(Telefones telefones) {

        return TelefoneRepresentation.builder()
                .codigoTelefone(telefones.getUuidTelefone())
                .ddi(telefones.getDdi())
                .ddd(telefones.getDdd())
                .numero(telefones.getNumero())
                .tipoTelefone(telefones.getTipoTelefoneEnum().toString())
                .status(telefones.getStatus())
                .descricao(telefones.getDescricao())
                .pessoa(getPessoaID(telefones))
                .build();
    }

    public List<TelefoneRepresentation> toListTelefoneRepresentation(List<Telefones> telefoneList) {

        return telefoneList.stream()
                .map(this::toTelefoneRepresentation)
                .collect(Collectors.toList());
    }

    private PessoaIdRRepresentation getPessoaID(Telefones telefones) {
        return PessoaIdRRepresentation.builder()
                .codigoPessoa(telefones.getPessoas().getUuidPessoa())
                .build();
    }
}
