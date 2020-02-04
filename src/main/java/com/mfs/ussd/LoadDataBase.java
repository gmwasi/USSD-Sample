package com.mfs.ussd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDataBase {
	 @Bean
	  CommandLineRunner initDatabase(PersonRepository personRepository, SessionRepository sessionRepository) {
	    return args -> {
	      
	    };
	  }
}
