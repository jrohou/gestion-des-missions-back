package dev.gestionmissions.controller;


import java.util.List;
import java.util.Map;

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

import dev.gestionmissions.entity.Mission;
import dev.gestionmissions.entity.Statut;
import dev.gestionmissions.repository.MissionRepository;
import dev.gestionmissions.repository.NatureRepository;
import dev.gestionmissions.service.MissionService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/missions")
public class MissionController {
	@Autowired
	private MissionRepository missionRepository;

	@Autowired
	private MissionService missionService;

	@Autowired private NatureRepository natureRepository;
  
	
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
	
	@SuppressWarnings("static-access")
	@PutMapping(value="/{id}")
	 public Mission changerStatutMission(@PathVariable int id, @RequestBody Map<String, String> statut) {
		
		Mission mission = this.missionRepository.findOne(id);
		if( statut.get("statut").equals("accepte")) {
			mission.setStatut(Statut.VALIDEE);
		}
		else if (statut.get("statut").equals("rejetee")) {
			mission.setStatut(Statut.REJETEE);
		}
		
		return missionRepository.save(mission);
		
	 }
	
	 @DeleteMapping(value="/{id}")
	 public List<Mission> deleteCollegue(@PathVariable int id) {
		this.missionRepository.delete(this.missionRepository.findOne(id));
		return this.missionRepository.findAll();
	 }
}
