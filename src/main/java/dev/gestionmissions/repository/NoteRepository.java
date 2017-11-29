package dev.gestionmissions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gestionmissions.entity.Mission;
import dev.gestionmissions.entity.Note;

public interface NoteRepository  extends JpaRepository<Note, Integer>{
	List<Note> findByMissionId(Integer id);
}
