package dev.gestionmissions.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gestionmissions.entity.Mission;
import dev.gestionmissions.entity.Transport;
import dev.gestionmissions.repository.MissionRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/missions")
public class MissionController {

	@Autowired
	private MissionRepository missionRepository;

	@GetMapping("")
	public List<Mission> listerMissions() {
		return missionRepository.findAll();
	}

	@GetMapping("/{id}")
	public Mission trouverMission(@PathVariable Integer id) {
		return missionRepository.findOne(id);
	}
	
	@PostMapping("")
	public boolean ajouterMission(@Valid @RequestBody Mission m, BindingResult resultatValidation){
		if (m.getDateDebut()==null){
			return false;
		}
		if (m.getDateFin()==null){
			return false;
		}
		if(m.getDateDebut().isBefore(LocalDate.now()) || m.getDateDebut().isEqual(LocalDate.now())){
			return false;
		}
		if(m.getTransport().equals(Transport.AVION)){
			if(m.getDateDebut().isBefore(LocalDate.now().plusDays(7))){
				return false;
			}
		}
		
		this.missionRepository.save(m);
		return true;
	}
}
