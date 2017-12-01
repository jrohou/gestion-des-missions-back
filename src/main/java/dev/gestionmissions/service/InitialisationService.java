package dev.gestionmissions.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.gestionmissions.entity.Mission;
import dev.gestionmissions.entity.Nature;
import dev.gestionmissions.entity.Statut;
import dev.gestionmissions.entity.Transport;
import dev.gestionmissions.repository.MissionRepository;
import dev.gestionmissions.repository.NatureRepository;
import dev.gestionmissions.repository.NoteRepository;

@Component
public class InitialisationService {

	@Autowired
	private NatureRepository natureRepository;

	@Autowired
	private MissionRepository missionRepository;

	@Autowired
	private NoteRepository noteRepository;

	public void init(){
		Stream.of(new Nature("Conseil", LocalDate.of(1990, 5, 25), LocalDate.of(2020, 5, 25), true, true, new BigDecimal(10), new BigDecimal(3.5)),
				 new Nature("Expertise Technique", LocalDate.of(2010, 5, 25), LocalDate.of(2035, 5, 25), true, true, new BigDecimal(25), new BigDecimal(4)),
				 new Nature("Formation", LocalDate.of(2016, 5, 25), LocalDate.of(2050, 5, 25), false, false, new BigDecimal(0), new BigDecimal(0)))
				.forEach(nature->{this.natureRepository.save(nature);});
		
		List<Nature> natureList = this.natureRepository.findAll();
		
		Stream.of(new Mission(LocalDate.of(2017, 1, 5), LocalDate.of(2017, 2, 15), natureList.get(0), "Toulouse", "Toulon", Statut.INITIALE, Transport.COVOITURAGE, new BigDecimal(15)),
				new Mission(LocalDate.of(2012, 1, 5), LocalDate.of(2013, 2, 15), natureList.get(1), "Nantes", "Paris", Statut.VALIDEE, Transport.TRAIN, new BigDecimal(15)),
				new Mission(LocalDate.of(2016, 11, 5), LocalDate.of(2016, 12, 15), natureList.get(2), "Strasbourg", "Paris", Statut.REJETEE, Transport.VOITURE, new BigDecimal(15)),
				new Mission(LocalDate.of(2017, 1, 5), LocalDate.of(2017, 2, 15), natureList.get(0), "Toulouse", "Limoges", Statut.EN_ATTENTE_VALIDATION, Transport.AVION, new BigDecimal(15)))
		.forEach(mission -> {this.missionRepository.save(mission);});
	}
}
