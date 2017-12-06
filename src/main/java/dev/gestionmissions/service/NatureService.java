package dev.gestionmissions.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.gestionmissions.entity.Nature;
import dev.gestionmissions.exception.ControleException;
import dev.gestionmissions.exception.DoublonNatureException;
import dev.gestionmissions.exception.PourcentagePrimeException;
import dev.gestionmissions.exception.TjmException;
import dev.gestionmissions.repository.MissionRepository;
import dev.gestionmissions.repository.NatureRepository;


@Service
public class NatureService {

	@Autowired
	private NatureRepository natureRepository;
	
	@Autowired
	private MissionRepository missionRepository;
	
	public boolean sauvegarderNature(Nature nature) throws ControleException{
		
		if(nature.getNom() == null) {
			return "Entrer un nom de nature correct" != null;
		}
		else if (isDoublon(nature)){
			throw new DoublonNatureException();
		}
		else if(( nature.isVersementPrime() == true && nature.getPourcentagePrime() != null && nature.getPourcentagePrime().floatValue() > 10))
		{
			throw new PourcentagePrimeException();
		}
		else if( (nature.getTauxJournalierMoyen() == null && nature.isFacturee() == true) ) {
			throw new TjmException();
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
	
	public boolean estSupprimable(int id){
		return !this.missionRepository.findAll().stream()
		.filter(mission -> mission.getNature().getId()==id && mission.getDateFin().isAfter(LocalDate.now()))
		.findAny().isPresent();
	}
	
	public void deleteNature(int id){
		if(this.estSupprimable(id)){
			if(this.missionRepository.findAll().stream()
			.filter(mission -> mission.getNature().getId()==id).findAny().isPresent()){
				Nature nat = this.natureRepository.findOne(id);
				nat.setDateFinValidite(LocalDate.now());
				this.natureRepository.save(nat);
			}else{
				this.natureRepository.delete(id);
			}
		}
	}
	
	public List<Nature> listerNaturesValides(){
		return this.natureRepository.findAll().stream().filter(mission-> mission.getDateFinValidite()==null).collect(Collectors.toList());
	}
	
	
}
