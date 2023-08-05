package com.allan.videolocadora;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.allan.videolocadora.model.Movie;
import com.allan.videolocadora.repository.MovieRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(MovieRepository movieRepository) {
		return args -> {
			movieRepository.deleteAll();
			movieRepository.save(new Movie("A Freira", "Terror"));
		};
	}

}
