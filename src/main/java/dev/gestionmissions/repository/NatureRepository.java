package dev.gestionmissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gestionmissions.entity.Nature;

public interface NatureRepository  extends JpaRepository<Nature, Integer>{

}
