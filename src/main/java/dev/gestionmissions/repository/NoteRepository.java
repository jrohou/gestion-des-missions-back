package dev.gestionmissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gestionmissions.entity.Note;

public interface NoteRepository  extends JpaRepository<Note, Integer>{

}
