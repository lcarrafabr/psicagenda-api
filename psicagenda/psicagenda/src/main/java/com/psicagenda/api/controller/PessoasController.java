package com.psicagenda.api.controller;


import com.psicagenda.api.mapper.PessoaMapper;
import com.psicagenda.api.mapper.representations.PessoaRepesentation;
import com.psicagenda.api.mapper.representations.PessoaRepresentationInput;
import com.psicagenda.api.model.Pessoas;
import com.psicagenda.api.rerpository.PessoaRepository;
import com.psicagenda.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoasController {


    @Autowired
    private PessoaRepository repository;

    @Autowired
    private PessoaMapper pessoaMapper;

    @Autowired
    private PessoaService pessoaService;


    @GetMapping
    public List<PessoaRepesentation> listarPessoas() {

        return pessoaMapper.toListPessoaRepresentation(repository.findAll());
    }

    @GetMapping("/{codigoPessoa}")
    public PessoaRepesentation buscaPessoaPorId(@PathVariable String codigoPessoa) {

        return pessoaMapper.toPessoaResponseRepresentation(pessoaService.buscaPessoaPorId(codigoPessoa));
    }

    @PutMapping("/{codigoPessoa}")
    public ResponseEntity<PessoaRepesentation> atualizarPessoa(@Valid @RequestBody PessoaRepesentation pessoaRepesentation,
                                                               @PathVariable String codigoPessoa) {

        Pessoas pessoaAtualziada = pessoaService.atualizarPessoa(pessoaRepesentation);

        return ResponseEntity.ok(pessoaMapper.toPessoaResponseRepresentation(pessoaAtualziada));
    }

    @DeleteMapping("/{codigoPessoa}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void removerPessoa(@PathVariable String codigoPessoa) {

        pessoaService.removerPessoa(codigoPessoa);
    }

    @PutMapping("/{codigoPessoa}/atualizar-tipo-cliente")
    public void atualizaStatusCliente(@PathVariable String codigoPessoa,
                                      @RequestParam("tipoCliente") String tipoCLiente) {


        pessoaService.atualizaTipoCliente(codigoPessoa, tipoCLiente);
    }

    @PutMapping("/{codigoPessoa}/status")
    public void atualizarStatusAtivo(@PathVariable String codigoPessoa,@RequestBody Boolean ativo) {


        pessoaService.atualizaStatusAtivo(codigoPessoa, ativo);
    }
}
