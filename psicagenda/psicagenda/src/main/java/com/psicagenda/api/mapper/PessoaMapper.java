package com.psicagenda.api.mapper;

import com.psicagenda.api.enums.TipoCliente;
import com.psicagenda.api.mapper.representations.PessoaRepesentation;
import com.psicagenda.api.mapper.representations.PessoaRepresentationInput;
import com.psicagenda.api.model.Pessoas;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PessoaMapper {


    public PessoaRepesentation toPessoaResponseRepresentation(Pessoas pessoas) {

        return PessoaRepesentation.builder()
                .codigoPessoa(pessoas.getUuidPessoa())
                .nomePessoa(pessoas.getNomePessoa())
                .dataCadastro(pessoas.getDataCadastro())
                .status(pessoas.getStatus())
                .build();
    }

    public List<PessoaRepesentation> toListPessoaRepresentation(List<Pessoas> pessoasList) {

        return pessoasList.stream()
                .map(this::toPessoaResponseRepresentation)
                .collect(Collectors.toList());
    }

    public Pessoas toPessoaModelRepresentation(PessoaRepresentationInput pessoaInput) {

        return Pessoas.builder()
                .nomePessoa(pessoaInput.getNome())
                .tipoCliente(getTipoEnum(pessoaInput.getTipoCLiente()))
                .build();
    }

    public TipoCliente getTipoEnum(String tipoCLiente) {

        if(TipoCliente.USER_PROFESSIONAL.toString().equals(tipoCLiente)) {
            return TipoCliente.USER_PROFESSIONAL;
        } else {
            return TipoCliente.USER_DEFAULT;
        }
    }
}
