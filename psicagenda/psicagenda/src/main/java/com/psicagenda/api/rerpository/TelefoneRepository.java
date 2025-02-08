package com.psicagenda.api.rerpository;

import com.psicagenda.api.model.Telefones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefones, Long> {
}
