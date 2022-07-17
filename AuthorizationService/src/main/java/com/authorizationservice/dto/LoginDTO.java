package com.authorizationservice.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {

    @Email(message = "wrong email format.")
    private String email;

    @NotBlank(message = "password cannot be blank.")
    private String password;
}
