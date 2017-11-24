package dev.gestionmissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gestionmissions.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Integer>{

}
