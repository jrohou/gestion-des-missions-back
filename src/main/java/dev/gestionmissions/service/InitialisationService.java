package dev.gestionmissions.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.gestionmissions.entity.Mission;
import dev.gestionmissions.entity.Nature;
import dev.gestionmissions.entity.NatureNote;
import dev.gestionmissions.entity.Note;
import dev.gestionmissions.entity.Statut;
import dev.gestionmissions.entity.Transport;
import dev.gestionmissions.entity.User;
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
	@Autowired private UserService userController;
	@Autowired private TraitementMissions traitement;
	
	@Autowired
	private TransportRepository transportRepository;

	public void init(){
		Stream.of(new Transport("Avion"), new Transport("Covoiturage"),new Transport("Voiture"),new Transport("Train")).forEach(transport->{transportRepository.save(transport);});
		List<Transport> listTransport = transportRepository.findAll();
		
		Stream.of(new Nature("Conseil", LocalDate.of(1990, 5, 25), null, true, true, new BigDecimal(10), new BigDecimal(10)),
				 new Nature("Expertise Technique", LocalDate.of(2010, 5, 25), null, true, true, new BigDecimal(25), new BigDecimal(10)),
				 new Nature("Formation", LocalDate.of(2016, 5, 25), null, false, false, new BigDecimal(0), new BigDecimal(0)))
				.forEach(nature->{this.natureRepository.save(nature);});
		
		List<Nature> natureList = this.natureRepository.findAll();
		
		Stream.of(
				new Mission(LocalDate.of(2017, 1, 5), LocalDate.of(2017, 2, 15), natureList.get(0), "Toulouse", "Toulon", Statut.INITIALE, listTransport.get(0), new BigDecimal(100), "75e8048c"),
				new Mission(LocalDate.of(2015, 2, 7), LocalDate.of(2015, 3, 15), natureList.get(2), "Nantes", "Paris", Statut.INITIALE, listTransport.get(1), new BigDecimal(200), "a8fc21fc"),
				new Mission(LocalDate.of(2014, 3, 6), LocalDate.of(2014, 4, 6), natureList.get(1), "Troie", "Istanbul", Statut.INITIALE, listTransport.get(2), new BigDecimal(244), "e300fb12"),
				new Mission(LocalDate.of(2012, 12, 1), LocalDate.of(2012, 12, 15), natureList.get(1), "Tours", "Lille", Statut.INITIALE, listTransport.get(3), new BigDecimal(572), "6c8be60e"),
				new Mission(LocalDate.of(2017, 5, 10), LocalDate.of(2017, 6, 10), natureList.get(0), "Marseille", "Junon", Statut.INITIALE, listTransport.get(0), new BigDecimal(142), "7befca85"),
				new Mission(LocalDate.of(2017, 10, 2), LocalDate.of(2017, 12, 2), natureList.get(1), "Toulouse", "Toulon", Statut.EN_ATTENTE_VALIDATION, listTransport.get(1), new BigDecimal(852), "8b2d3ac7"),
				new Mission(LocalDate.of(2016, 1, 10), LocalDate.of(2016, 3, 10), natureList.get(2), "Nantes", "Paris", Statut.EN_ATTENTE_VALIDATION, listTransport.get(2), new BigDecimal(70), "740ef3fd"),
				new Mission(LocalDate.of(2017, 2, 20), LocalDate.of(2017, 3, 20), natureList.get(0), "Troie", "Istanbul", Statut.EN_ATTENTE_VALIDATION, listTransport.get(3), new BigDecimal(15), "6b7325b5"),
				new Mission(LocalDate.of(2017, 6, 2), LocalDate.of(2017, 7, 2), natureList.get(1), "Tours", "Lille", Statut.EN_ATTENTE_VALIDATION, listTransport.get(0), new BigDecimal(800), "3dfba579"),
				new Mission(LocalDate.of(2017, 10, 1), LocalDate.of(2017, 12, 1), natureList.get(2), "Marseille", "Junon", Statut.EN_ATTENTE_VALIDATION, listTransport.get(1), new BigDecimal(1520), "4aef1639"),
				new Mission(LocalDate.of(2017, 1, 7), LocalDate.of(2017, 1, 15), natureList.get(0), "Toulouse", "Toulon", Statut.INITIALE, listTransport.get(2), new BigDecimal(1000), "bd540e65"),
				new Mission(LocalDate.of(2018, 2, 4), LocalDate.of(2018, 2, 6), natureList.get(2), "Nantes", "Paris", Statut.INITIALE, listTransport.get(3), new BigDecimal(150), "a8fc21fc"),
				new Mission(LocalDate.of(2019, 8, 7), LocalDate.of(2019, 8, 10), natureList.get(1), "Troie", "Istanbul", Statut.INITIALE, listTransport.get(0), new BigDecimal(15), "e300fb12"),
				new Mission(LocalDate.of(2016, 12, 4), LocalDate.of(2016, 12, 15), natureList.get(1), "Tours", "Lille", Statut.INITIALE, listTransport.get(1), new BigDecimal(54), "6c8be60e"),
				new Mission(LocalDate.of(2017, 1, 5), LocalDate.of(2017, 2, 5), natureList.get(0), "Marseille", "Junon", Statut.INITIALE, listTransport.get(2), new BigDecimal(15), "7befca85"),
				new Mission(LocalDate.of(2012, 8, 5), LocalDate.of(2012, 8, 10), natureList.get(1), "Toulouse", "Toulon", Statut.EN_ATTENTE_VALIDATION, listTransport.get(3), new BigDecimal(25), "bd540e65"),
				new Mission(LocalDate.of(2014, 12, 1), LocalDate.of(2014, 12, 20), natureList.get(2), "Nantes", "Paris", Statut.EN_ATTENTE_VALIDATION, listTransport.get(0), new BigDecimal(15), "a8fc21fc"),
				new Mission(LocalDate.of(2017, 4, 4), LocalDate.of(2017, 6, 4), natureList.get(0), "Troie", "Istanbul", Statut.EN_ATTENTE_VALIDATION, listTransport.get(1), new BigDecimal(81), "e300fb12"),
				new Mission(LocalDate.of(2016, 5, 5), LocalDate.of(2016, 8, 5), natureList.get(1), "Tours", "Lille", Statut.EN_ATTENTE_VALIDATION, listTransport.get(2), new BigDecimal(15), "6c8be60e"),
				new Mission(LocalDate.of(2018, 4, 5), LocalDate.of(2018, 4, 15), natureList.get(2), "Marseille", "Junon", Statut.EN_ATTENTE_VALIDATION, listTransport.get(3), new BigDecimal(8), "7befca85"),
				new Mission(LocalDate.of(2019, 4, 5), LocalDate.of(2019, 7, 5), natureList.get(1), "Toulouse", "Toulon", Statut.VALIDEE, listTransport.get(0), new BigDecimal(15), "75e8048c"),
				new Mission(LocalDate.of(2018, 4, 5), LocalDate.of(2018, 6, 5), natureList.get(2), "Nantes", "Paris", Statut.VALIDEE, listTransport.get(1), new BigDecimal(2), "75e8048c"),
				new Mission(LocalDate.of(2015, 6, 5), LocalDate.of(2015, 6, 15), natureList.get(0), "Troie", "Istanbul", Statut.VALIDEE, listTransport.get(2), new BigDecimal(15), "e300fb12"),
				new Mission(LocalDate.of(2013, 1, 5), LocalDate.of(2013, 2, 6), natureList.get(1), "Tours", "Lille", Statut.VALIDEE, listTransport.get(3), new BigDecimal(18), "6c8be60e"),
				new Mission(LocalDate.of(2012, 7, 5), LocalDate.of(2012, 8, 5), natureList.get(2), "Marseille", "Junon", Statut.VALIDEE, listTransport.get(0), new BigDecimal(20), "e300fb12"),
				new Mission(LocalDate.of(2017, 1, 7), LocalDate.of(2017, 1, 15), natureList.get(1), "Toulouse", "Toulon", Statut.VALIDEE, listTransport.get(1), new BigDecimal(15), "75e8048c"),
				new Mission(LocalDate.of(2011, 6, 5), LocalDate.of(2011, 7, 5), natureList.get(2), "Nantes", "Paris", Statut.VALIDEE, listTransport.get(2), new BigDecimal(1), "f26eac86"),
				new Mission(LocalDate.of(2011, 1, 5), LocalDate.of(2011, 2, 5), natureList.get(0), "Troie", "Istanbul", Statut.VALIDEE, listTransport.get(3), new BigDecimal(15), "e300fb12"),
				new Mission(LocalDate.of(2010, 7, 7), LocalDate.of(2011, 7, 7), natureList.get(1), "Tours", "Lille", Statut.VALIDEE, listTransport.get(0), new BigDecimal(52), "6c8be60e"),
				new Mission(LocalDate.of(2012, 6, 5), LocalDate.of(2012, 7, 5), natureList.get(2), "Marseille", "Junon", Statut.VALIDEE, listTransport.get(1), new BigDecimal(15), "26a79080"),
				new Mission(LocalDate.of(2018, 1, 5), LocalDate.of(2018, 2, 5), natureList.get(2), "Grenoble", "Dijon", Statut.REJETEE, listTransport.get(2), new BigDecimal(80), "6c8be60e"),
				new Mission(LocalDate.of(2019, 7, 5), LocalDate.of(2019, 7, 15), natureList.get(2), "Atlanta", "Saint-Ouen", Statut.REJETEE, listTransport.get(3), new BigDecimal(100), "f26eac86"),
				new Mission(LocalDate.of(2014, 6, 5), LocalDate.of(2014, 7, 5), natureList.get(2), "New York", "Singapour", Statut.REJETEE, listTransport.get(0), new BigDecimal(15), "26a79080"),
				new Mission(LocalDate.of(2012, 1, 5), LocalDate.of(2012, 2, 5), natureList.get(2), "Toulouse", "Tokyo", Statut.REJETEE, listTransport.get(1), new BigDecimal(60), "e300fb12"),
				new Mission(LocalDate.of(2012, 7, 5), LocalDate.of(2012, 7, 15), natureList.get(2), "Strasbourg", "Brest", Statut.REJETEE, listTransport.get(2), new BigDecimal(15), "7befca85"),
				new Mission(LocalDate.of(2013, 1, 5), LocalDate.of(2013, 1, 15), natureList.get(2), "Quimper", "Le Pellerin", Statut.REJETEE, listTransport.get(3), new BigDecimal(10), "26a79080"),
				new Mission(LocalDate.of(2014, 6, 5), LocalDate.of(2014, 7, 5), natureList.get(2), "Bordeaux", "Paris", Statut.REJETEE, listTransport.get(0), new BigDecimal(20), "a8fc21fc"),
				new Mission(LocalDate.of(2015, 1, 5), LocalDate.of(2015, 1, 7), natureList.get(2), "Bouguenais", "Montpellier", Statut.REJETEE, listTransport.get(1), new BigDecimal(15), "bd540e65"),
				new Mission(LocalDate.of(2016, 7, 5), LocalDate.of(2016, 8, 5), natureList.get(2), "Coueron", "Bouayes", Statut.REJETEE, listTransport.get(2), new BigDecimal(18), "e300fb12"))
		.forEach(mission -> {this.missionRepository.save(mission);});
	
		List<Mission> missionList = this.missionRepository.findAll();
		
		Stream.of(new NatureNote("Hotel"),
				new NatureNote("Restaurant"),
				new NatureNote("Petit dejeuner"),
				new NatureNote("Voiture"))
		.forEach(nature -> {this.natureNoteRepository.save(nature);});

		List<NatureNote> natureNotesList = this.natureNoteRepository.findAll();
		
		Stream.of(			
				new Note(LocalDate.of(2017, 1, 10), natureNotesList.get(0), new BigDecimal(100), missionList.get(0)),
				new Note(LocalDate.of(2015, 2, 9), natureNotesList.get(2), new BigDecimal(200), missionList.get(1)),
				new Note(LocalDate.of(2014, 3, 8), natureNotesList.get(1), new BigDecimal(244), missionList.get(2)),
				new Note(LocalDate.of(2012, 12, 5), natureNotesList.get(1), new BigDecimal(572), missionList.get(3)),
				new Note(LocalDate.of(2017, 5, 17), natureNotesList.get(0), new BigDecimal(142), missionList.get(4)),
				new Note(LocalDate.of(2017, 10, 5), natureNotesList.get(1), new BigDecimal(852), missionList.get(5)),
				new Note(LocalDate.of(2016, 1, 11), natureNotesList.get(2), new BigDecimal(70), missionList.get(6)),
				new Note(LocalDate.of(2017, 2, 25), natureNotesList.get(0), new BigDecimal(15), missionList.get(7)),
				new Note(LocalDate.of(2017, 6, 8), natureNotesList.get(1), new BigDecimal(800), missionList.get(8)),
				new Note(LocalDate.of(2017, 10, 5), natureNotesList.get(2), new BigDecimal(1520), missionList.get(9)),
				new Note(LocalDate.of(2017, 1, 10), natureNotesList.get(0), new BigDecimal(1000), missionList.get(10)))
				.forEach(natureNote -> {this.noteRepository.save(natureNote);});
		
		try {
			userController.findUsers();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		traitement.scheduledTask();
		
	}
}
