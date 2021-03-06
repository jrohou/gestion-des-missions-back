package dev.gestionmissions.controller;

import java.time.LocalDate;
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

import dev.gestionmissions.entity.Nature;
import dev.gestionmissions.repository.MissionRepository;
import dev.gestionmissions.repository.NatureRepository;
import dev.gestionmissions.service.NatureService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/natures")
public class NatureController {
	
	@Autowired
	private NatureRepository natureRepository;
	
	
	
	@Autowired
	private NatureService natService;
	
	/*
	 * Recherche et affiche la liste des Natures
	 */
	@GetMapping
	public List<Nature> listerNature() {
		return this.natService.listerNaturesValides();
	}
		
	@PostMapping
	public boolean ajouterNatureMission (@RequestBody Nature nature ){
		return this.natService.sauvegarderNature(nature);
	}
	
	@PutMapping(value="/{id}")
	 public Nature majNatureMission(@RequestBody Nature nature) {
		return this.natureRepository.save(nature);
	 }
	
	@DeleteMapping(value="/{id}")
	 public List<Nature> deleteCollegue(@PathVariable int id) {
		this.natService.deleteNature(id);
		return this.natService.listerNaturesValides();
	 }
	
	@GetMapping("/{id}/deletable")
	public boolean naturePeutEtreSupprimee(@PathVariable int id){
		return this.natService.estSupprimable(id);
	}
}
