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

import dev.gestionmissions.entity.Mission;
import dev.gestionmissions.exception.ValidationMissionException;
import dev.gestionmissions.repository.MissionRepository;
import dev.gestionmissions.service.MissionService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/missions")
public class MissionController {
	@Autowired
	private MissionRepository missionRepository;

	@Autowired
	private MissionService missionService;

	@GetMapping
	public List<Mission> listerMissions() {
		return missionRepository.findAll();
	}

	@GetMapping("/{id}")
	public Mission trouverMission(@PathVariable Integer id) {
		return missionRepository.findOne(id);
	}

	@PutMapping("/{id}")
	public Mission changerMission(@PathVariable Integer id, @RequestBody Mission mission)
			throws ValidationMissionException {
		return this.missionService.modifierMission(mission);
	}

	@PostMapping()
	public Mission ajouterMission(@RequestBody Mission mission) throws ValidationMissionException {
		return this.missionService.sauvegarderMission(mission);
	}

	@DeleteMapping(value = "/{id}")
	public List<Mission> deleteMission(@PathVariable int id) {
		this.missionRepository.delete(this.missionRepository.findOne(id));
		return this.missionRepository.findAll();
	}

}
