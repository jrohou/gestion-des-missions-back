package dev.gestionmissions.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import dev.gestionmissions.entity.Mission;
import dev.gestionmissions.entity.Statut;
import dev.gestionmissions.repository.MissionRepository;

@EnableScheduling
public class TraitementMissions {

	@Autowired
	private MissionRepository missionRepository;

	@Autowired
	private MissionService missionService;

	@Scheduled(cron = "0 0 * * *") // tous les jours à minuit
	public void scheduledTask() {
		List<Mission> missions = this.missionRepository.findAll();

		missions.stream().filter(mission -> mission.getStatut().equals(Statut.INITIALE)).forEach(mission -> {
			mission.setStatut(Statut.EN_ATTENTE_VALIDATION);
			this.missionRepository.save(mission);
		});

		missions.stream().filter(mission -> mission.getDateFin().isAfter(LocalDate.now())).forEach(mission -> {
			mission.setMontantPrime(mission.getNature().getTauxJournalierMoyen()
					.multiply(missionService.calculerNbJoursTravailles(mission))
					.multiply(mission.getNature().getPourcentagePrime()).divide(new BigDecimal(100)));
			missionRepository.save(mission);
		});
	}

}
