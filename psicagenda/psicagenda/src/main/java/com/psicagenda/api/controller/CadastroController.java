package com.psicagenda.api.controller;

import com.psicagenda.api.mapper.UsuarioMapper;
import com.psicagenda.api.mapper.representations.CadastroRepresentationInput;
import com.psicagenda.api.mapper.representations.UsuarioRepresentation;
import com.psicagenda.api.model.Usuarios;
import com.psicagenda.api.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cadastro")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @Autowired
    private UsuarioMapper usuarioMapper;


    @PostMapping
    public ResponseEntity<UsuarioRepresentation> cadsatrarUsuario(@Valid @RequestBody CadastroRepresentationInput cadastro,
                                                                  @RequestParam("tipoCliente")String tipoCLiente, HttpServletResponse response) {

        Usuarios usuarioCadastrado = cadastroService.cadastrarUsuarioProfessional(cadastro, tipoCLiente, response);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                usuarioMapper.toUsuarioRepresentation(usuarioCadastrado)
        );
    }
}
