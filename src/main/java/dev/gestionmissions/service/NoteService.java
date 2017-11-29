package dev.gestionmissions.service;

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
	
	public String sauvegarderNote(Note note){
		note.setDate(this.dateUtil.convertJSDateToJavaLocalDate(note.getDate()));
		if (note.getDate()==null){
			return "Entrez des dates";
		}
		if(note.getDate().isBefore(note.getMission().getDateDebut())){
			return "La date de la note ne peut pas être avant le début de la mission";
		}
		if(note.getDate().isAfter(note.getMission().getDateFin())){
			return "La date de fin ne peut pas être après la fin de la mission";
		}
		this.noteRepository.save(note);
		return "success";
	}

}