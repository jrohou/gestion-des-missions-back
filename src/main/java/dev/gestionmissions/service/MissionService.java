package dev.gestionmissions.service;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.gestionmissions.entity.Mission;
import dev.gestionmissions.entity.Transport;
import dev.gestionmissions.exception.ValidationMissionException;
import dev.gestionmissions.repository.MissionRepository;
import dev.gestionmissions.repository.TransportRepository;

@Service
public class MissionService {

	@Autowired
	private MissionRepository missionRepository;

	@Autowired
	private TransportRepository transportRepository;

	@Autowired
	private DateUtil dateUtil;

	public Mission sauvegarderMission(Mission mission) throws ValidationMissionException {
		mission.setDateDebut(this.dateUtil.convertJSDateToJavaLocalDate(mission.getDateDebut()));
		mission.setDateFin(this.dateUtil.convertJSDateToJavaLocalDate(mission.getDateFin()));
		if (mission.getDateDebut() == null && mission.getDateFin() == null) {
			throw new ValidationMissionException("Entrez des dates");
		}
		if (mission.getDateDebut().isBefore(LocalDate.now())) {
			throw new ValidationMissionException("La date de début doit être au minimum demain");
		}
		if (mission.getDateFin().isBefore(mission.getDateDebut())) {
			throw new ValidationMissionException("La date de fin ne peut pas être avant la date de début");
		}
		if (mission.getTransport().equals(transportRepository.findTransportBymodeTransport("Avion"))) {
			if (mission.getDateDebut().isBefore(LocalDate.now().plusDays(7))) {
				throw new ValidationMissionException(
						"La date de début doit être au mininmum dans une semaine (Transport=Avion)");
			}
		}
		if (mission.getDateDebut().getDayOfWeek().equals(DayOfWeek.SUNDAY)
				|| mission.getDateDebut().getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
			throw new ValidationMissionException("La mission ne peut pas commencer le weekend");
		}
		if (mission.getDateFin().getDayOfWeek().equals(DayOfWeek.SUNDAY)
				|| mission.getDateFin().getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
			throw new ValidationMissionException("La mission ne peut pas se terminer le weekend");
		}
		return this.missionRepository.save(mission);
	}
	
	public Mission modifierMission(Mission mission) throws ValidationMissionException {
		mission.setDateDebut(this.dateUtil.convertJSDateToJavaLocalDate(mission.getDateDebut()));
		mission.setDateFin(this.dateUtil.convertJSDateToJavaLocalDate(mission.getDateFin()));
		if (mission.getDateDebut() == null && mission.getDateFin() == null) {
			throw new ValidationMissionException("Entrez des dates");
		}
		if (mission.getDateFin().isBefore(mission.getDateDebut())) {
			throw new ValidationMissionException("La date de fin ne peut pas être avant la date de début");
		}
		if (mission.getDateDebut().getDayOfWeek().equals(DayOfWeek.SUNDAY)
				|| mission.getDateDebut().getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
			throw new ValidationMissionException("La mission ne peut pas commencer le weekend");
		}
		if (mission.getDateFin().getDayOfWeek().equals(DayOfWeek.SUNDAY)
				|| mission.getDateFin().getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
			throw new ValidationMissionException("La mission ne peut pas se terminer le weekend");
		}
		return this.missionRepository.save(mission);
	}
}
