package com.psicagenda.api.controller;

import com.psicagenda.api.mapper.UsuarioMapper;
import com.psicagenda.api.mapper.representations.UsuarioRepresentation;
import com.psicagenda.api.mapper.representations.UsuarioRepresentationInput;
import com.psicagenda.api.model.Usuarios;
import com.psicagenda.api.rerpository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private UsuariosRepository repository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @GetMapping
    public List<UsuarioRepresentation> listarUsuarios() {

        return usuarioMapper.toUsuarioRepresentationList(
                repository.findAll()
        );
    }

    public ResponseEntity<UsuarioRepresentation> cadastrarUsuario(@Valid @RequestBody UsuarioRepresentationInput usuarioInput,
                                                                  HttpServletResponse response) {

        return null;
    }
}
