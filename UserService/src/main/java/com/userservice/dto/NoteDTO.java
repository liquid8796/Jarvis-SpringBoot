package com.userservice.dto;

import com.userservice.entity.User;
import com.userservice.enums.NoteType;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NoteDTO {

    private Long note_id;

    @NotBlank(message = "title cannot be blank.")
    private String title;

    private String body;

    @NotNull(message = "note_type cannot be null.")
    private NoteType note_type;

    private String description;

    @NotNull(message = "created_by cannot be null.")
    private Long created_by;

    private Long updated_by;

    @NotNull(message = "user cannot be null.")
    private User user;
}
