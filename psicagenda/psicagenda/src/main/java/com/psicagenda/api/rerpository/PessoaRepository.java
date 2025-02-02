package com.psicagenda.api.rerpository;

import com.psicagenda.api.model.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoas, Long> {


    @Query(nativeQuery = true,
    value = "select * from pessoas " +
            "where uuid_pessoa = :codigoPessoa ")
    Optional<Pessoas> buscaPorUuId(@Param("codigoPessoa")String codigoPessoa);

    @Query(nativeQuery = true,
    value = "delete from pessoas " +
            "where uuid_pessoa = :codigoPessoa ")
    void deleteByUuidPessoa(@Param("codigoPessoa") String codigoPessoa);
}
