package com.userservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Long user_id;
    @NotBlank(message = "username cannot be blank.")
    private String username;

    private String password;

    @NotBlank(message = "fullname cannot be blank.")
    private String full_name;
    @NumberFormat()
    private int age;
    @NotBlank(message = "email cannot be blank.")
    @Email(message = "wrong email format.")
    private String email;
    private String fcm_token;
    @NotNull(message = "role cannot be blank.")
    private RoleDTO role;
    private List<TagDTO> tags;
    private List<NoteDTO> notes;
}
