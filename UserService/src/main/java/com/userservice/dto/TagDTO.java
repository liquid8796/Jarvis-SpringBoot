package com.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagDTO {

    private Long tag_id;
    private String title;
    private String link;
    private String description;
    private UserDTO user;
}
