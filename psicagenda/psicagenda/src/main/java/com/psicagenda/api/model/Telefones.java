package com.psicagenda.api.model;

import com.psicagenda.api.enums.TipoTelefoneEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "telefones")
public class Telefones {

    @Id
    @Column(name = "telefone_id")
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long telefoneId;

    @NotBlank
    @Column(name = "uuid_telefone", length = 36, updatable = false)
    private String uuidTelefone;

    @NotBlank
    @Column(length = 4)
    private String ddi;

    @NotBlank
    @Column(length = 3)
    private String ddd;

    @NotBlank
    @Column(length = 10)
    private String numero;

    @NotNull
    @Column(name = "tipo_telefone", length = 45)
    @Enumerated(EnumType.STRING)
    private TipoTelefoneEnum tipoTelefoneEnum;

    @NotNull
    private Boolean status;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoas pessoas;

    @PrePersist
    void aoCadastrar() {

        setUuidTelefone(UUID.randomUUID().toString());
        setDdd(String.format("%03d", Integer.parseInt(ddd)));
        setStatus(true);
        verificaFormatoDdi();
    }

    private void verificaFormatoDdi() {

        if (ddi != null && !ddi.matches("^\\+\\d{1,3}$")) {
            setDdi("+".concat(ddi));
        }

    }
}
