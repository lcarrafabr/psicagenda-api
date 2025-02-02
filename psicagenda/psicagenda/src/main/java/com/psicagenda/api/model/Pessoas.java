package com.psicagenda.api.model;

import com.psicagenda.api.enums.TipoCliente;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pessoas")
public class Pessoas {

    @Id
    @Column(name = "pessoa_id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pessoaId;

    @Column(name = "uuid_pessoa", length = 36, updatable = false)
    private String uuidPessoa;

    @NotBlank
    @Column(name = "nome_pessoa", length = 255)
    private String nomePessoa;

    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    private Boolean status;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;


    @PrePersist
    public void aocadastrarNovo() {
        aoCadastrar();
    }

    @PreUpdate
    public void aoAtualizar() {

        if(StringUtils.hasLength(nomePessoa)){
            nomePessoa = nomePessoa.toUpperCase().trim();
        }

    }

    void aoCadastrar() {

        if(StringUtils.hasLength(nomePessoa)){

            nomePessoa = nomePessoa.toUpperCase().trim();
            status = true;
            dataCadastro = LocalDateTime.now();
            setUuidPessoa(UUID.randomUUID().toString());
        }
    }

}
