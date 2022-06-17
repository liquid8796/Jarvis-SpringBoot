package com.userservice.service;

import com.userservice.dto.NoteDTO;
import com.userservice.model.Response;

import java.util.List;

public interface NoteService {

    Response<List<NoteDTO>> getNotes();

    Response<Boolean> checkCodeExist(String title);

    Response<NoteDTO> addNote(NoteDTO note);

    Response<NoteDTO> updateNote(NoteDTO note);

    void deleteNote(Long id);
}
