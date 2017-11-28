package dev.gestionmissions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gestionmissions.entity.Transport;
import dev.gestionmissions.repository.TransportRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/transports")
public class TransportController {

	@Autowired
	private TransportRepository transportRepository;

	@GetMapping
	public List<Transport> listerTransport() {
		return this.transportRepository.findAll();
	}
}
