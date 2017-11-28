package dev.gestionmissions.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import dev.gestionmissions.service.InitialisationService;

@Component
public class InitListener {

	@Autowired
	private InitialisationService init;
	
	@EventListener({ ContextRefreshedEvent.class })
	void contextRefreshedEvent() {
		
		init.init();
	}
}
