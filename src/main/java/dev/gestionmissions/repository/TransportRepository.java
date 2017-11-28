package dev.gestionmissions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.gestionmissions.entity.Transport;


public interface TransportRepository extends JpaRepository<Transport, Integer>{

	Transport findTransportBymodeTransport(String modeTransport);
}
