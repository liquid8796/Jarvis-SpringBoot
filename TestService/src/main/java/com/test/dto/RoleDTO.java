package com.test.dto;

import com.test.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleDTO {

    private Long role_id;
    private String role;
    private String role_status;
    private String description;
    private List<User> users;
}
