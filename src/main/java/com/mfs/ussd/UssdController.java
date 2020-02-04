package com.mfs.ussd;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UssdController {

	private final PersonRepository personRepository;
	private final SessionRepository sessionRepository;

	UssdController(PersonRepository personRepository, SessionRepository sessionRepository) {
		this.personRepository = personRepository;
		this.sessionRepository = sessionRepository;
	}

	@GetMapping("ussd")
	ResponseEntity<String> get(@RequestParam String phoneNumber, @RequestParam String sessionId,
			@RequestParam String ussdText) {
		String[] level = ussdText.split("\\*", 0);
		String response = "";
		if(ussdText == "") {
			//create session in in-memory DB
			Session newSess = new Session();
			newSess.setName(sessionId);
			newSess.setPhoneNumber(phoneNumber);
			sessionRepository.save(newSess);
			
			response = "CON What would you want to check \n" + "1. Register \n" + "2. View Status";
		} else {
			if (level[0].contentEquals("1") && level.length == 1) {
				// Check if session exists
				List<Session> sessions = sessionRepository.findAll();
				Session sess = sessions.stream()
						  .filter(s -> sessionId.equals(s.getName()))
						  .findAny()
						  .orElse(null);
				// If no session exists save session in in-memory DB
				if(sess == null) {
					Session newSess = new Session();
					newSess.setName(sessionId);
					newSess.setPhoneNumber(phoneNumber);
					sessionRepository.save(newSess);
				}
				response = "CON Please enter your full name";
			} else if (level[0].contentEquals("2") && level.length == 1) {
				// Check if session exists
				List<Session> sessions = sessionRepository.findAll();
				Session sess = sessions.stream()
						  .filter(s -> sessionId.equals(s.getName()))
						  .findAny()
						  .orElse(null);
				// If no session exists save session in in-memory DB
				if(sess == null) {
					Session newSess = new Session();
					newSess.setName(sessionId);
					newSess.setPhoneNumber(phoneNumber);
					sessionRepository.save(newSess);
				}
				String details = "No details found!";
				List<Person> people = personRepository.findAll();
				Person per = people.stream()
						.filter(p -> phoneNumber.equals(p.getPhoneNumber()))
						.findAny()
						.orElse(null);
				if(per != null) {
					Person person = people.get(0);
					details = "Name: " +person.getName() + "\n Identification Number: " + person.getIdNumber();
				}
				response = "END " + details;
			} else if (level[0].contentEquals("1") && !level[1].contentEquals("") && level.length == 2) {
				// Check if session exists
				List<Session> sessions = sessionRepository.findAll();
				Session sess = sessions.stream()
						  .filter(s -> sessionId.equals(s.getName()))
						  .findAny()
						  .orElse(null);
				// If no session exists save session in in-memory DB
				if(sess == null) {
					Session newSess = new Session();
					newSess.setName(sessionId);
					newSess.setPhoneNumber(phoneNumber);
					sessionRepository.save(newSess);
				}
				response = "CON Please enter your Identification Number";
			} else if (level[0].contentEquals("1") && !level[1].contentEquals("") && !level[2].contentEquals("") && level.length == 3) {
				// Check if session exists
				List<Session> sessions = sessionRepository.findAll();
				Session sess = sessions.stream()
						  .filter(s -> sessionId.equals(s.getName()))
						  .findAny()
						  .orElse(null);
				// If no session exists save session in in-memory DB
				if(sess == null) {
					Session newSess = new Session();
					newSess.setName(sessionId);
					newSess.setPhoneNumber(phoneNumber);
					sessionRepository.save(newSess);
				}
				List<Person> people = personRepository.findAll();
				Person per = people.stream()
						.filter(p -> level[2].equals(p.getPhoneNumber()))
						.findAny()
						.orElse(null);
				if(per == null) {
					try {
						Person user = new Person();
						user.setName(level[1]);
						user.setIdNumber(level[2]);
						user.setPhoneNumber(phoneNumber);
						personRepository.save(user);
						
						response = "END Details saved successfully";
						// delete session
						sessionRepository.deleteAll();
					} catch (Exception e) {
						response = "END Could not save user";
						// delete session
						sessionRepository.deleteAll();
					}
					
				} else {
					response = "END User already exists!";
					// delete session
					sessionRepository.deleteAll();
				}
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		return ResponseEntity.ok(response);
	}
}
