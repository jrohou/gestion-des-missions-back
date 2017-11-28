package dev.gestionmissions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gestionmissions.entity.Nature;
import dev.gestionmissions.repository.NatureRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/natures")
public class NatureController {
	@Autowired
	private NatureRepository natureRepository;

	@GetMapping
	public List<Nature> listerNature() {
		return this.natureRepository.findAll();
	}

}
