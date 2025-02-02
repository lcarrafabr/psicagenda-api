package com.psicagenda.api.service.serviceimpl;

import com.psicagenda.api.event.RecursoCriadoEvent;
import com.psicagenda.api.exception.EntidadeNaoEncontradaException;
import com.psicagenda.api.exception.NegocioException;
import com.psicagenda.api.mapper.UsuarioMapper;
import com.psicagenda.api.mapper.representations.CadastroRepresentationInput;
import com.psicagenda.api.mapper.representations.UsuarioRepresentationInput;
import com.psicagenda.api.model.Pessoas;
import com.psicagenda.api.model.Usuarios;
import com.psicagenda.api.rerpository.UsuariosRepository;
import com.psicagenda.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private static final String EMAIL_EXISTENTE_EM_USO = "Este e-mail já está em uso.";

    @Autowired
    private UsuariosRepository repository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public Usuarios cadastrarUsuario(CadastroRepresentationInput cadastroInput, HttpServletResponse response, Pessoas pessoas) {

        try {

            Optional<Usuarios> usuariosExistente = repository.findByEmail(cadastroInput.getEmail());

            if(usuariosExistente.isPresent()) {
                throw new NegocioException(EMAIL_EXISTENTE_EM_USO);
            }

            Usuarios usuarioToSave = usuarioMapper.toUsuarioModel(cadastroInput, pessoas);
            Usuarios usuarioSalvo = repository.save(usuarioToSave);

            publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioToSave.getUsuarioId()));

            return usuarioSalvo;

        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
