package com.psicagenda.api.service;

import com.psicagenda.api.mapper.representations.CadastroRepresentationInput;
import com.psicagenda.api.mapper.representations.UsuarioRepresentationInput;
import com.psicagenda.api.model.Pessoas;
import com.psicagenda.api.model.Usuarios;

import javax.servlet.http.HttpServletResponse;

public interface UsuarioService {

    Usuarios cadastrarUsuario(CadastroRepresentationInput cadastroInput, HttpServletResponse response,
                              Pessoas pessoas);
}
