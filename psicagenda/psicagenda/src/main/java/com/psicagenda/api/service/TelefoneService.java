package com.psicagenda.api.service;

import com.psicagenda.api.mapper.representations.TelefoneRepresentationInput;
import com.psicagenda.api.model.Telefones;

import javax.servlet.http.HttpServletResponse;

public interface TelefoneService {

    Telefones cadastrarTelefone(TelefoneRepresentationInput telefoneInput, HttpServletResponse response, String tokenId);

    Telefones buscaTelefonePorCodigoTelefone(String codigoTelefone, String tokenId);

    Telefones atualizaTelefoneProfissional(String tokenId, String codigoTelefone, TelefoneRepresentationInput telefoneInput);
}
