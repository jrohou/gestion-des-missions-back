package dev.gestionmissions.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.gestionmissions.entity.Mission;
import dev.gestionmissions.entity.Nature;
import dev.gestionmissions.entity.NatureNote;
import dev.gestionmissions.entity.Note;
import dev.gestionmissions.entity.Statut;
import dev.gestionmissions.entity.Transport;
import dev.gestionmissions.repository.MissionRepository;
import dev.gestionmissions.repository.NatureNoteRepository;
import dev.gestionmissions.repository.NatureRepository;
import dev.gestionmissions.repository.NoteRepository;
import dev.gestionmissions.repository.TransportRepository;

@Component
public class InitialisationService {

	@Autowired private NatureRepository natureRepository;
	@Autowired private MissionRepository missionRepository;
	@Autowired private NatureNoteRepository natureNoteRepository;
	@Autowired private NoteRepository noteRepository;
	
	@Autowired
	private TransportRepository transportRepository;

	public void init(){
		Stream.of(new Transport("Avion"), new Transport("Covoiturage"),new Transport("Voiture"),new Transport("Train")).forEach(transport->{transportRepository.save(transport);});
		List<Transport> listTransport = transportRepository.findAll();
		Stream.of(new Nature("Conseil", LocalDate.of(1990, 5, 25), LocalDate.of(2020, 5, 25), true, true, new BigDecimal(10)),
				 new Nature("Expertise Technique", LocalDate.of(2010, 5, 25), LocalDate.of(2035, 5, 25), true, true, new BigDecimal(25)),
				 new Nature("Formation", LocalDate.of(2016, 5, 25), LocalDate.of(2050, 5, 25), false, false, new BigDecimal(0)))
				.forEach(nature->{this.natureRepository.save(nature);});
		
		List<Nature> natureList = this.natureRepository.findAll();
		
		Stream.of(new Mission(LocalDate.of(2017, 1, 5), LocalDate.of(2017, 2, 15), natureList.get(0), "Toulouse", "Toulon", Statut.INITIALE, listTransport.get(0), new BigDecimal(15)),
				new Mission(LocalDate.of(2012, 1, 5), LocalDate.of(2013, 2, 15), natureList.get(1), "Nantes", "Paris", Statut.VALIDEE, listTransport.get(1), new BigDecimal(15)),
				new Mission(LocalDate.of(2016, 11, 5), LocalDate.of(2016, 12, 15), natureList.get(2), "Strasbourg", "Paris", Statut.REJETEE, listTransport.get(2), new BigDecimal(15)),
				new Mission(LocalDate.of(2017, 1, 5), LocalDate.of(2017, 2, 15), natureList.get(0), "Toulouse", "Limoges", Statut.EN_ATTENTE_VALIDATION, listTransport.get(3), new BigDecimal(15)))
		.forEach(mission -> {this.missionRepository.save(mission);});
	
		List<Mission> missionList = this.missionRepository.findAll();
		
		Stream.of(new NatureNote("Hotel"),
				new NatureNote("Restaurant"),
				new NatureNote("Petit dejeuner"),
				new NatureNote("Voiture"))
		.forEach(nature -> {this.natureNoteRepository.save(nature);});

		List<NatureNote> natureNotesList = this.natureNoteRepository.findAll();
		
		Stream.of(new Note(LocalDate.of(2017, 1, 6), natureNotesList.get(3), new BigDecimal(15), missionList.get(0)),
				new Note(LocalDate.of(2012, 1, 6), natureNotesList.get(2), new BigDecimal(17), missionList.get(1)),
				new Note(LocalDate.of(2016, 11, 6), natureNotesList.get(1), new BigDecimal(20), missionList.get(2)),
				new Note(LocalDate.of(2017, 1, 6), natureNotesList.get(0), new BigDecimal(12), missionList.get(3)))
		.forEach(natureNote -> {this.noteRepository.save(natureNote);});

	}
}
