package com.allan.videolocadora;

import com.allan.videolocadora.model.Class;
import com.allan.videolocadora.repository.ClassRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.allan.videolocadora.model.Actor;
import com.allan.videolocadora.repository.ActorRepository;

import java.util.Date;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
