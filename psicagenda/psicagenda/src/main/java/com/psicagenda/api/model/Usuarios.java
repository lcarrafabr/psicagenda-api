package com.psicagenda.api.model;

import com.psicagenda.api.utils.FuncoesUtils;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @Column(name = "usuario_id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    @NotBlank
    @Column(name = "uuid_usuario", length = 36, updatable = false)
    private String uuidUsuario;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String senha;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoas pessoas;

    @PrePersist
    public void aoCadastrar() {
        toLowerCase();
        encriptaPassword();
        uuidUsuario = UUID.randomUUID().toString();
    }

    @PreUpdate
    public void aoAtualizar() {
        toLowerCase();
    }

    private void toLowerCase() {

        email = email.toLowerCase();
    }

    private void encriptaPassword() {

        //senha = FuncoesUtils.geraPasswordCrippt(senha.trim());
        senha = "1234";
    }
}
