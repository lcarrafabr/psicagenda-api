package com.psicagenda.api.service.serviceimpl;

import com.psicagenda.api.exception.EntidadeNaoEncontradaException;
import com.psicagenda.api.exception.NegocioException;
import com.psicagenda.api.mapper.PessoaMapper;
import com.psicagenda.api.mapper.representations.CadastroRepresentationInput;
import com.psicagenda.api.mapper.representations.PessoaRepresentationInput;
import com.psicagenda.api.model.Usuarios;
import com.psicagenda.api.service.CadastroService;
import com.psicagenda.api.service.PessoaService;
import com.psicagenda.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class CadastroServiceImpl implements CadastroService {


    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaMapper pessoaMapper;

    @Autowired
    private UsuarioService usuarioService;


    @Transactional
    @Override
    public Usuarios cadastrarUsuarioProfessional(CadastroRepresentationInput cadastro, String tipoCLiente, HttpServletResponse response) {

        try {
            var pessoaInput = pessoaMapper.toPessoaRepresentationInput(cadastro, tipoCLiente);
            var pessoaCadastrada = pessoaService.cadastrarPessoa(pessoaInput, response);

            var usuarioCadastrado = usuarioService.cadastrarUsuario(cadastro, response, pessoaCadastrada);

            return usuarioCadastrado;

            //TODO cadastrar permissões para usuario PROFESSIONAL
            //TODO colocar um if para o tipoCliente e decidir quais permissões seerão cadastradas
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
