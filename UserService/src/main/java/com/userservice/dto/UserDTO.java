package com.userservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private Long user_id;
    private String username;
    private String full_name;
    private int age;
    private String email;
    private String fcm_token;
    private RoleDTO role;
    private List<TagDTO> tags;
}
