package com.psicagenda.api.controller;

import com.psicagenda.api.mapper.TelefoneMapper;
import com.psicagenda.api.mapper.representations.TelefoneRepresentation;
import com.psicagenda.api.model.Telefones;
import com.psicagenda.api.rerpository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/telefones")
public class TelefoneController {

    @Autowired
    private TelefoneRepository repository;

    @Autowired
    private TelefoneMapper telefoneMapper;

    @GetMapping
    public ResponseEntity<List<TelefoneRepresentation>> listarTelefones() {

        List<TelefoneRepresentation> telefonesList = telefoneMapper.toListTelefoneRepresentation(
                repository.findAll()
        );

        return ResponseEntity.ok().body(telefonesList);
    }
}
