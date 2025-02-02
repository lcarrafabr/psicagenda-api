package com.psicagenda.api.service;

import com.psicagenda.api.mapper.representations.PessoaRepesentation;
import com.psicagenda.api.mapper.representations.PessoaRepresentationInput;
import com.psicagenda.api.model.Pessoas;

import javax.servlet.http.HttpServletResponse;

public interface PessoaService {

    Pessoas cadastrarPessoa(PessoaRepresentationInput pessoaInput, HttpServletResponse response);

    Pessoas buscaPessoaPorId(String codigoPessoa);

    Pessoas atualizarPessoa(PessoaRepesentation pessoaRepesentation);

    void removerPessoa(String codigoPessoa);

    void atualizaTipoCliente(String codigoPessoa, String tipoCliente);

    void atualizaStatusAtivo(String codigoPessoa, Boolean ativo);
}
