package dev.gestionmissions.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

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

import dev.gestionmissions.entity.User;

import dev.gestionmissions.exception.ValidationMissionException;
import dev.gestionmissions.repository.MissionRepository;
import dev.gestionmissions.repository.NoteRepository;
import dev.gestionmissions.service.MissionService;
import dev.gestionmissions.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/missions")
public class MissionController {
	@Autowired
	private MissionRepository missionRepository;
	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private UserService userController;
	@Autowired
	private MissionService missionService;

	@GetMapping
	public List<Mission> listerMissions() {
		return missionRepository.findAll();
	}

	@GetMapping("/matricule/{matricule}")
	public List<Mission> listerMissionsByUser(@PathVariable String matricule) {
		return missionRepository.findMissionByMatricule(matricule);
	}

	@GetMapping("/{id}")
	public Mission trouverMission(@PathVariable Integer id) {
		return missionRepository.findOne(id);
	}
	
	@GetMapping("/nature/{id}")
	public boolean trouverNatureMission(@PathVariable Integer id) {
		return missionRepository.findAll().stream()
				.filter(mission -> mission.getDateFin().isBefore(LocalDate.now()) && mission.getNature().getId() == id)
				.findAny()
				.isPresent();
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

	@SuppressWarnings("static-access")
	@PutMapping(value = "/statut/{id}")
	public Mission changerStatutMission(@PathVariable int id, @RequestBody Map<String, String> statut) {

		Mission mission = this.missionRepository.findOne(id);
		if (statut.get("statut").equals("accepte")) {
			mission.setStatut(Statut.VALIDEE);
		} else if (statut.get("statut").equals("rejetee")) {
			mission.setStatut(Statut.REJETEE);
		}

		return missionRepository.save(mission);

	}

	@DeleteMapping(value = "/{id}")
	@Transactional()
	public List<Mission> deleteMission(@PathVariable int id) {
		this.noteRepository.deleteByMissionId(id);
		this.missionRepository.delete(this.missionRepository.findOne(id));
		return this.missionRepository.findAll();
	}

	@GetMapping("/subalternes/{matricule}")
	public List<Mission> getMissionBySubalterne(@PathVariable String matricule) {
		User user = userController.users.get(matricule);
		List<Mission> subalternesMission = new ArrayList<>();
		for (String subalterne : user.getSubalternes()) {
			for (Mission mission : missionRepository.findMissionByMatricule(subalterne)) {
				subalternesMission.add(mission);
			}
		}

		return subalternesMission;
	}
}
