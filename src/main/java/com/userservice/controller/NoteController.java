package com.userservice.controller;

import com.userservice.dto.NoteDTO;
import com.userservice.dto.UserDTO;
import com.userservice.model.Response;
import com.userservice.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping(value = "/list", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Response<List<NoteDTO>> getNotes(){
        return noteService.getNotes();
    }

    @PostMapping(value = "/create", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Response<NoteDTO> createNote(@Valid @RequestBody NoteDTO note){
        return noteService.addNote(note);
    }

    @GetMapping(value = "/addCode")
    public Response<Boolean> addCodeJav(@RequestHeader Long userId, @RequestBody List<String> code){
        return noteService.addCodeJav(code, userId);
    }

    @PutMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Response<NoteDTO> updateNote(@Valid @RequestBody NoteDTO note){
        return noteService.updateNote(note);
    }

    @DeleteMapping("/delete/{id}")
    public Response<Boolean> deleteUser(@PathVariable Long id){
        noteService.deleteNote(id);
        return new Response<>(200, Boolean.TRUE);
    }

    @GetMapping(value = "/jav/exist", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Response<Boolean> checkCodeExist(@RequestParam String title){
        return noteService.checkCodeExist(title);
    }
}
