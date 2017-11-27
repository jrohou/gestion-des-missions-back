package dev.gestionmissions.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.annotation.PostConstruct;

import dev.gestionmissions.entity.Nature;

public class NatureController {
 @PostConstruct
 public void initNature() {
	 Nature nature = new Nature("mission", LocalDate.of(2017, 02, 02), LocalDate.of(2017, 03, 02), false, false, new BigDecimal(15));

 }
}
