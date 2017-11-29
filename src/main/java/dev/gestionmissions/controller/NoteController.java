package dev.gestionmissions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gestionmissions.entity.Note;
import dev.gestionmissions.repository.MissionRepository;
import dev.gestionmissions.repository.NoteRepository;
import dev.gestionmissions.service.NoteService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/notes")
public class NoteController {

	@Autowired private MissionRepository missionRepository;
	@Autowired private NoteRepository noteRepository;
	@Autowired private NoteService noteService;
	
	@GetMapping
	public List<Note> listerNotes() {
		return noteRepository.findAll();
	}

	@GetMapping("/{id}")
	public List<Note> trouverNotesDeMission(@PathVariable Integer id) {
		return noteRepository.findByMissionId(id);
	}
	
	@PutMapping("/{id}")
	public Note changerNote(@PathVariable Integer id, @RequestBody Note note) {
		return noteRepository.save(note);
	}
	

	@PostMapping()
	public String ajouterNote(@RequestBody Note note ){
		return this.noteService.sauvegarderNote(note);
	}
	

	 @DeleteMapping(value="/{id}")
	 public List<Note> deleteNote(@PathVariable int id) {
		this.noteRepository.delete(this.noteRepository.findOne(id));
		return this.noteRepository.findAll();
	 }
}
