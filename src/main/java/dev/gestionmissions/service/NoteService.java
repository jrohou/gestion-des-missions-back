package dev.gestionmissions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.gestionmissions.entity.Note;
import dev.gestionmissions.repository.NoteRepository;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private DateUtil dateUtil;
	
	public List<Note> sauvegarderNote(Note note){
		note.setDate(this.dateUtil.convertJSDateToJavaLocalDate(note.getDate()));
		if (note.getDate()==null){
			return null;
		}
		if(note.getDate().isBefore(note.getMission().getDateDebut())){
			return null;
		}
		if(note.getDate().isAfter(note.getMission().getDateFin())){
			return null;
		}
		this.noteRepository.save(note);
		return this.noteRepository.findByMissionId(note.getMission().getId());
	}

}