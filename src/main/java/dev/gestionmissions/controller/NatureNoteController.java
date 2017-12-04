package dev.gestionmissions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gestionmissions.entity.NatureNote;
import dev.gestionmissions.repository.NatureNoteRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/nature-notes")
public class NatureNoteController {
	@Autowired private NatureNoteRepository natureNoteRepository;
	
	@GetMapping
	public List<NatureNote> listerNotes() {
		return natureNoteRepository.findAll();
	}

}
