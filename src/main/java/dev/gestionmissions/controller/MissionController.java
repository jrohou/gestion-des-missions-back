package dev.gestionmissions.controller;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gestionmissions.entity.Mission;

import dev.gestionmissions.repository.MissionRepository;
import dev.gestionmissions.service.MissionService;

import dev.gestionmissions.entity.Statut;
import dev.gestionmissions.entity.Transport;
import dev.gestionmissions.repository.NatureRepository;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/missions")
public class MissionController {
	@Autowired
	private MissionRepository missionRepository;

	@Autowired
	private MissionService missionService;

	@Autowired private NatureRepository natureRepository;
  
	@PostConstruct
	public void initMissions() {		
		missionRepository.save(new Mission(LocalDate.of(2017, 02, 02), LocalDate.of(2017, 05, 02), natureRepository.findOne(0), "toulouse", "nantes", Statut.INITIALE, Transport.COVOITURAGE, new BigDecimal(15)));
	}
	
	@GetMapping
	public List<Mission> listerMissions() {
		return missionRepository.findAll();
	}

	@GetMapping("/{id}")
	public Mission trouverMission(@PathVariable Integer id) {
		return missionRepository.findOne(id);
	}
	

	@PostMapping
	public String ajouterMission (@RequestBody Mission mission ){
		return this.missionService.sauvegarderMission(mission);
	}
	

	 @DeleteMapping(value="/{id}")
	 public List<Mission> deleteCollegue(@PathVariable int id) {
		this.missionRepository.delete(this.missionRepository.findOne(id));
		return this.missionRepository.findAll();
	 }
}
