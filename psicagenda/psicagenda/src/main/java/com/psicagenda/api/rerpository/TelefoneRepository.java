package com.psicagenda.api.rerpository;

import com.psicagenda.api.model.Telefones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefones, Long> {
    @Query(nativeQuery = true,
    value = "select * from telefones " +
            "where uuid_telefone = :codigoTelefone " +
            "and pessoa_id = :codigoPessoa ")
    Optional<Telefones> findByCodigoTelefoneECodigoPessoa(@Param("codigoTelefone") String codigoTelefone,
                                               @Param("codigoPessoa") Long codigoPessoa);
}
