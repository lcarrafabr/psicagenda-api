package com.psicagenda.api.mapper.representations;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioRepresentationInput {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
