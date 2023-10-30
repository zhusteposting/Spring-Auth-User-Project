package com.eposting.epost.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordRequest {
    @NotBlank
    @Email
    private String oldPass;

    @NotBlank
    private String newPass;
}