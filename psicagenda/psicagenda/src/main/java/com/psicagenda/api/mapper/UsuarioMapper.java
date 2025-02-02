package com.psicagenda.api.mapper;

import com.psicagenda.api.mapper.representations.CadastroRepresentationInput;
import com.psicagenda.api.mapper.representations.PessoaRepesentation;
import com.psicagenda.api.mapper.representations.UsuarioRepresentation;
import com.psicagenda.api.model.Pessoas;
import com.psicagenda.api.model.Usuarios;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    public UsuarioRepresentation toUsuarioRepresentation(Usuarios usuarios) {

        return UsuarioRepresentation.builder()
                .codigoUsuario(usuarios.getUuidUsuario())
                .email(usuarios.getEmail())
                .pessoa(getPessoaRepresentation(usuarios))
                .build();
    }

    public List<UsuarioRepresentation> toUsuarioRepresentationList(List<Usuarios> usuariosList) {

        return usuariosList.stream()
                .map(this::toUsuarioRepresentation)
                .collect(Collectors.toList());
    }


    public Usuarios toUsuarioModel(CadastroRepresentationInput cadastro, Pessoas pessoas) {

        return Usuarios.builder()
                .email(cadastro.getEmail())
                .senha(cadastro.getSenha())
                .pessoas(pessoas)
                .build();
    }

    private PessoaRepesentation getPessoaRepresentation(Usuarios usuarios) {

        return PessoaRepesentation.builder()
                .codigoPessoa(usuarios.getPessoas().getUuidPessoa())
                .nomePessoa(usuarios.getPessoas().getNomePessoa())
                .dataCadastro(usuarios.getPessoas().getDataCadastro())
                .status(usuarios.getPessoas().getStatus())
                .build();
    }
}
