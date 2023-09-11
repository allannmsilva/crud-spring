package com.allan.videolocadora;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.allan.videolocadora.model.Actor;
import com.allan.videolocadora.repository.ActorRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ActorRepository actorRepository) {
		return args -> {
			actorRepository.deleteAll();
			actorRepository.save(new Actor("Ryan Reynolds"));
		};
	}

}
