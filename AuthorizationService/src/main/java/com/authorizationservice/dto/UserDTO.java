package com.authorizationservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UserDTO {

    private Long user_id;
    @NotBlank(message = "username cannot be blank.")
    private String username;
    @NotBlank(message = "password cannot be blank.")
    private String password;
    @NotBlank(message = "fullname cannot be blank.")
    private String full_name;
    @NumberFormat()
    private int age;
    @NotBlank(message = "email cannot be blank.")
    @Email(message = "wrong email format.")
    private String email;
    private String fcm_token;
//    @NotBlank(message = "role cannot be blank.")
//    private String role;
}
