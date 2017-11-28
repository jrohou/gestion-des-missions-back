package dev.gestionmissions.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gestionmissions.entity.Nature;
import dev.gestionmissions.repository.NatureRepository;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/natures")
public class NatureController {
	@Autowired
	NatureRepository natureRepository;
 @PostConstruct
 public void initNature() {
	 natureRepository.save(new Nature("mission", LocalDate.of(2017, 02, 02), LocalDate.of(2017, 03, 02), false, false, new BigDecimal(15)));

 }
}
