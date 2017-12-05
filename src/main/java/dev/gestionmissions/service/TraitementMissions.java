package dev.gestionmissions.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailSenderValidatorAutoConfiguration;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import dev.gestionmissions.entity.Mission;
import dev.gestionmissions.entity.Statut;
import dev.gestionmissions.repository.MissionRepository;

@EnableScheduling
@Service
public class TraitementMissions {

	@Autowired
	private MissionRepository missionRepository;

	@Autowired
	private MissionService missionService;

	@Autowired
	private EmailServiceImpl email;

	@Scheduled(cron = "0 0 0 * * *") // tous les jours à minuit
	public void scheduledTask() {
		List<Mission> missions = this.missionRepository.findAll();

		missions.stream().filter(mission -> mission.getStatut().equals(Statut.INITIALE)).forEach(mission -> {
			mission.setStatut(Statut.EN_ATTENTE_VALIDATION);
			email.sendSimpleMessage("benjamin.lasseur@gmail.com", "Nouvelle mission à valider", "La mission "
					+ mission.getId() + " de l'employé " + mission.getMatricule() + " est en attente de validation.");
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
