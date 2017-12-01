package dev.gestionmissions.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.gestionmissions.entity.Nature;
import dev.gestionmissions.exception.ControleException;
import dev.gestionmissions.exception.DoublonNatureException;
import dev.gestionmissions.exception.PourcentagePrimeException;
import dev.gestionmissions.repository.NatureRepository;


@Service
public class NatureService {

	@Autowired
	private NatureRepository natureRepository;
	
	public boolean sauvegarderNature(Nature nature) throws ControleException{
		
		if(nature.getNom() == null) {
			return "Entrer un nom de nature correct" != null;
		}
		else if (isDoublon(nature)){
			throw new DoublonNatureException();
		}
		else if(nature.getPourcentagePrime()!=null && nature.getPourcentagePrime().floatValue() > 10)
		{
			throw new PourcentagePrimeException();
		}
		
		nature.setDateDebutValidite(LocalDate.now());
		nature.setDateFinValidite(null);
		
		this.natureRepository.save(nature);
		return true;
	}


	
	public boolean isDoublon(Nature nature) {
		Nature checkNature = natureRepository.findByNom(nature.getNom());
		
		if(checkNature != null) {
			return true;
		}
		return false;
	}
	
	
}
