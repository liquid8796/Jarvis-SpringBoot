package com.userservice.repository;

import com.userservice.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    Boolean existsNoteByTitle(String title);

//    @Modifying
//    @Query(value = "SELECT Note n.note_id WHERE n.title IN :userIds")
//    int updateCusKycMethod(, @Param("userIds") List<Long> userIds);
}
