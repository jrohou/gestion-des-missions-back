package dev.gestionmissions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gestionmissions.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Integer>{
	List<Mission> findMissionByMatricule(String matricule);
}
