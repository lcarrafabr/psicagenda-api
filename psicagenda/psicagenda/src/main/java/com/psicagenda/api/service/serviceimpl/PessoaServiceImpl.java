package com.psicagenda.api.service.serviceimpl;

import com.psicagenda.api.enums.TipoCliente;
import com.psicagenda.api.event.RecursoCriadoEvent;
import com.psicagenda.api.exception.EntidadeNaoEncontradaException;
import com.psicagenda.api.exception.NegocioException;
import com.psicagenda.api.exception.entidadeexception.EntidadeEmUsoException;
import com.psicagenda.api.mapper.PessoaMapper;
import com.psicagenda.api.mapper.representations.PessoaRepesentation;
import com.psicagenda.api.mapper.representations.PessoaRepresentationInput;
import com.psicagenda.api.model.Pessoas;
import com.psicagenda.api.model.exception.PessoaNaoEncontradaException;
import com.psicagenda.api.rerpository.PessoaRepository;
import com.psicagenda.api.service.PessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
public class PessoaServiceImpl implements PessoaService {

    private static final String CADASTRO_EM_USO = "O cadastro de código %s não pode ser removido pois está em uso.";

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private PessoaMapper mapper;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Transactional
    @Override
    public Pessoas cadastrarPessoa(PessoaRepresentationInput pessoaInput, HttpServletResponse response) {

        try {
            Pessoas pessoaToSave = mapper.toPessoaModelRepresentation(pessoaInput);
            Pessoas PessoaSalva = repository.save(pessoaToSave);

            publisher.publishEvent(new RecursoCriadoEvent(this, response, PessoaSalva.getPessoaId()));

            return PessoaSalva;

        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }


    @Override
    public Pessoas buscaPessoaPorId(String codigoPessoa) {
        return repository.buscaPorUuId(codigoPessoa).orElseThrow(() -> new PessoaNaoEncontradaException(codigoPessoa));
    }


    @Transactional
    @Override
    public Pessoas atualizarPessoa(PessoaRepesentation pessoaRepesentation) {

        try {
            Pessoas pessoaSalva = buscaPessoaPorId(pessoaRepesentation.getCodigoPessoa());
            BeanUtils.copyProperties(pessoaRepesentation, pessoaSalva, "pessoaId");

            return repository.save(pessoaSalva);
        }catch (PessoaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Transactional
    @Override
    public void removerPessoa(String codigoPessoa) {

        try {
            repository.deleteByUuidPessoa(codigoPessoa);

        } catch (EmptyResultDataAccessException e) {
            throw new PessoaNaoEncontradaException(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(CADASTRO_EM_USO, codigoPessoa));
        }
    }


    @Transactional
    @Override
    public void atualizaTipoCliente(String codigoPessoa, String tipoCliente) {

        try {
            Pessoas pessoaSalva = buscaPessoaPorId(codigoPessoa);

            var tipoClienteNovo = atualizarTipoCliente(mapper.getTipoEnum(tipoCliente));
            pessoaSalva.setTipoCliente(tipoClienteNovo);
            repository.save(pessoaSalva);

        } catch (PessoaNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }

    }

    @Override
    public void atualizaStatusAtivo(String codigoPessoa, Boolean ativo) {

        Pessoas pessoaSalva = buscaPessoaPorId(codigoPessoa);
        pessoaSalva.setStatus(ativo);
        repository.save(pessoaSalva);
    }


    private TipoCliente atualizarTipoCliente(TipoCliente tipoCliente) {

        TipoCliente tipoClienteRetorno = null;

        switch (tipoCliente) {

            case USER_PROFESSIONAL:
                tipoClienteRetorno =  TipoCliente.USER_PROFESSIONAL;
            break;

            case USER_DEFAULT:
                tipoClienteRetorno = TipoCliente.USER_DEFAULT;
            break;
        }

        return tipoClienteRetorno;
    }
}
