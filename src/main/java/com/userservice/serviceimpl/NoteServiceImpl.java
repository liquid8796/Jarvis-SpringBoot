package com.userservice.serviceimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userservice.dto.NoteDTO;
import com.userservice.entity.Note;
import com.userservice.entity.User;
import com.userservice.handler.JarvisException;
import com.userservice.model.Response;
import com.userservice.repository.NoteRepository;
import com.userservice.repository.UserRepository;
import com.userservice.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Response<List<NoteDTO>> getNotes() {
        List<Note> notes = noteRepository.findAll();

        List<NoteDTO> result = notes.stream()
                .map(d -> objectMapper.convertValue(d, NoteDTO.class))
                .collect(toList());
        return new Response<>(200, "Success", result);
    }

    @Override
    public Response<Boolean> checkCodeExist(String title) {
        Boolean result = noteRepository.existsNoteByTitle(title);

        return new Response<>(200, "Success", result);
    }

    @Override
    public Response<NoteDTO> addNote(NoteDTO dto) {
        Note note = objectMapper.convertValue(dto, Note.class);

        return new Response<>(200, "Success", objectMapper.convertValue(noteRepository.save(note), NoteDTO.class));
    }

    @Override
    public Response<NoteDTO> updateNote(NoteDTO dto) {
        Note note = noteRepository.findById(dto.getNote_id()).orElseThrow(() -> new JarvisException("Note not found.", 404));
        note = objectMapper.convertValue(dto, Note.class);
        User user = userRepository.findById(dto.getUser().getUser_id()).orElseThrow(() -> new JarvisException("User not found.", 404));
        note.setUser(user);

        return new Response<>(200, "Success", objectMapper.convertValue(noteRepository.save(note), NoteDTO.class));
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.findById(id).orElseThrow(() -> new JarvisException("Note not found.", 404));
        noteRepository.deleteById(id);
    }
}
