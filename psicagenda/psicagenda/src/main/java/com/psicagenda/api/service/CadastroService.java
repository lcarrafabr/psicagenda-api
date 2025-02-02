package com.psicagenda.api.service;

import com.psicagenda.api.mapper.representations.CadastroRepresentationInput;
import com.psicagenda.api.model.Usuarios;

import javax.servlet.http.HttpServletResponse;

public interface CadastroService {

    Usuarios cadastrarUsuarioProfessional(CadastroRepresentationInput cadastro,
                                          String tipoCLiente, HttpServletResponse response);
}
