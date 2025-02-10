package com.psicagenda.api.service.serviceimpl;

import com.psicagenda.api.event.RecursoCriadoEvent;
import com.psicagenda.api.exception.EntidadeNaoEncontradaException;
import com.psicagenda.api.exception.NegocioException;
import com.psicagenda.api.exception.entidadeexception.EntidadeEmUsoException;
import com.psicagenda.api.mapper.TelefoneMapper;
import com.psicagenda.api.mapper.representations.TelefoneRepresentationInput;
import com.psicagenda.api.model.Pessoas;
import com.psicagenda.api.model.Telefones;
import com.psicagenda.api.model.exception.TelefoneNaoEncontradoException;
import com.psicagenda.api.rerpository.TelefoneRepository;
import com.psicagenda.api.service.PessoaService;
import com.psicagenda.api.service.TelefoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
public class TelefoneServiceImpl implements TelefoneService {

    private static final String TELEFONE_EM_USO = "O telefone de código %s não pode ser removido, pois está em uso.";

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private TelefoneMapper telefoneMapper;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PessoaService pessoaService;

    @Transactional
    @Override
    public Telefones cadastrarTelefone(TelefoneRepresentationInput telefoneInput, HttpServletResponse response, String tokenId) {

        try {
            Pessoas pessoasSalva = pessoaService.buscaPessoaPorId(tokenId);
            Telefones telefoneToSave = telefoneMapper.toTelefoneModelRepresentation(telefoneInput,pessoasSalva);

            Telefones telefoneSalvo = telefoneRepository.save(telefoneToSave);
            publisher.publishEvent(new RecursoCriadoEvent(this, response, telefoneSalvo.getTelefoneId()));

            return telefoneSalvo;
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public Telefones buscaTelefonePorCodigoTelefone(String codigoTelefone, String tokenId) {

        Pessoas pessoas = pessoaService.buscaPessoaPorId(tokenId);
        return telefoneRepository.findByCodigoTelefoneECodigoPessoa(codigoTelefone, pessoas.getPessoaId())
                .orElseThrow(() -> new TelefoneNaoEncontradoException(codigoTelefone));
    }


    @Transactional
    @Override
    public Telefones atualizaTelefoneProfissional(String tokenId, String codigoTelefone, TelefoneRepresentationInput telefoneInput) {

        try {
            log.info("...: Atualizando telefone :...");

            Pessoas pessoa = pessoaService.buscaPessoaPorId(tokenId);
            Telefones telefoneToSave = telefoneMapper.toTelefoneModelRepresentation(telefoneInput, pessoa);
            Telefones telefoneSalvo = buscaTelefonePorCodigoTelefone(codigoTelefone, tokenId);

            BeanUtils.copyProperties(telefoneInput, telefoneSalvo, "telefoneId");

            var telefoneAtualizado = telefoneRepository.save(telefoneToSave);
            log.info("...: Telefone pessoaID {} atualizado :...", pessoa.getPessoaId());

            return telefoneAtualizado;

        } catch (TelefoneNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void removerTelefone(String codigoTelefone) {

        try {
            telefoneRepository.deleteByUuidTelefone(codigoTelefone);
        } catch (EmptyResultDataAccessException e ) {
            throw new TelefoneNaoEncontradoException(codigoTelefone);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(TELEFONE_EM_USO, codigoTelefone));
        }

    }

    @Transactional
    @Override
    public void atualizaStatusAtivo(String codigoTelefone, Boolean ativo) {

        try {
            Telefones telefoneSalvo = buscaTelefoneByUuidTelefone(codigoTelefone);
            telefoneSalvo.setStatus(ativo);
            telefoneRepository.save(telefoneSalvo);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public Telefones buscaTelefoneByUuidTelefone(String codigoTelefone) {

        return telefoneRepository.findByUuidTelefone(codigoTelefone).orElseThrow(() -> new TelefoneNaoEncontradoException(codigoTelefone));
    }
}
