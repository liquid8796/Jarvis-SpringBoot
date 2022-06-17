package com.userservice.repository;

import com.userservice.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Boolean existsNoteByTitle(String title);
}
