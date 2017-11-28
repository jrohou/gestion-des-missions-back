package dev.gestionmissions.service;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.gestionmissions.entity.Mission;
import dev.gestionmissions.entity.Transport;
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
	
	public String sauvegarderMission(Mission mission){
		mission.setDateDebut(this.dateUtil.convertJSDateToJavaLocalDate(mission.getDateDebut()));
		mission.setDateFin(this.dateUtil.convertJSDateToJavaLocalDate(mission.getDateFin()));
		if (mission.getDateDebut()==null && mission.getDateFin()==null){
			return "Entrez des dates";
		}
		if (mission.getDateDebut().isBefore(LocalDate.now())){
			return "La date de début doit être au minimum demain";
		}
		if(mission.getDateFin().isBefore(mission.getDateDebut())){
			return "La date de fin ne peut pas être avant la date de début";
		}
		if(mission.getTransport().equals(transportRepository.findTransportBymodeTransport("Avion"))){
			if(mission.getDateDebut().isBefore(LocalDate.now().plusDays(7))){
				return "La date de début doit être au mininmum dans une semaine (Transport=Avion)";
			}
		}
		if(mission.getDateDebut().getDayOfWeek().equals(DayOfWeek.SUNDAY)||mission.getDateDebut().getDayOfWeek().equals(DayOfWeek.SATURDAY)){
			return "La mission ne peut pas commencer le weekend";
		}
		if(mission.getDateFin().getDayOfWeek().equals(DayOfWeek.SUNDAY)||mission.getDateFin().getDayOfWeek().equals(DayOfWeek.SATURDAY)){
			return "La mission ne peut pas se terminer le weekend";
		}
		this.missionRepository.save(mission);
		return "success";
	}

}
