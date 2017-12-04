package dev.gestionmissions.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.gestionmissions.entity.User;

@Service
public class UserService {
	public Map<String, User> users = new HashMap<>();
	
	public Map<String, User> findUsers() throws IOException {
		String str = utils
				.get("https://raw.githubusercontent.com/DiginamicFormation/ressources-atelier/master/users.json");

		JsonFactory jsonFactory = new JsonFactory();

		@SuppressWarnings("deprecation")
		JsonParser jsonParser = jsonFactory.createJsonParser(str);
		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<User>> mapTypeUser = new TypeReference<List<User>>() {
		};

		List<User> liste = mapper.readValue(jsonParser, mapTypeUser);

		liste.forEach(userJson -> {
			users.put(userJson.getMatricule(), userJson);
		});
		
		return users;
	}

}
