package com.psicagenda.api.controller;

import com.psicagenda.api.mapper.TelefoneMapper;
import com.psicagenda.api.mapper.representations.TelefoneRepresentation;
import com.psicagenda.api.mapper.representations.TelefoneRepresentationInput;
import com.psicagenda.api.model.Telefones;
import com.psicagenda.api.rerpository.TelefoneRepository;
import com.psicagenda.api.service.TelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/telefones")
public class TelefoneController {

    @Autowired
    private TelefoneRepository repository;

    @Autowired
    private TelefoneMapper telefoneMapper;

    @Autowired
    private TelefoneService telefoneService;

    @GetMapping
    public ResponseEntity<List<TelefoneRepresentation>> listarTelefones() {

        List<TelefoneRepresentation> telefonesList = telefoneMapper.toListTelefoneRepresentation(
                repository.findAll()
        );

        return ResponseEntity.ok().body(telefonesList);
    }

    @PostMapping
    public ResponseEntity<TelefoneRepresentation> cadastrarTelefoneProfissional(@RequestParam("tokenId") String tokenId,
                                                                                @Valid @RequestBody TelefoneRepresentationInput telefoneInput,
                                                                                HttpServletResponse response) {

        Telefones telefoneSalvo = telefoneService.cadastrarTelefone(telefoneInput, response, tokenId);

        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneMapper.toTelefoneRepresentation(telefoneSalvo));
    }

    @GetMapping("/{codigoTelefone}")
    public ResponseEntity<TelefoneRepresentation> buscaPorId(@PathVariable String codigoTelefone, @RequestParam("tokenId") String tokenId) {

        return ResponseEntity.ok(telefoneMapper.toTelefoneRepresentation(
                telefoneService.buscaTelefonePorCodigoTelefone(codigoTelefone, tokenId)
        ));
    }

    @PutMapping("/{codigoTelefone}")
    public ResponseEntity<TelefoneRepresentation> atualizarTelefoneProfissional(@RequestParam("tokenId") String tokenId,
                                                                                @PathVariable String codigoTelefone,
                                                                                @Valid @RequestBody TelefoneRepresentationInput telefoneInput) {

        TelefoneRepresentation telefoneAtualizado = telefoneMapper.toTelefoneRepresentation(
                telefoneService.atualizaTelefoneProfissional(tokenId,codigoTelefone,telefoneInput)
        );

        return ResponseEntity.ok(telefoneAtualizado);
    }
}
