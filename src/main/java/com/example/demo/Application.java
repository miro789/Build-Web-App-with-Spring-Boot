package com.example.demo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.run.Location;
import com.example.demo.run.Run;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

		log.info("Application started successfully! - Mirone");

//		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
//
//		Object welcomeMessage = context.getBean("welcomeMessage");
//		System.out.println(welcomeMessage);

//		var welcomeMessage = new WelcomeMessage();
//		System.out.println(welcomeMessage.getWelcomeMessage());
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			Run run = new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 3,
					Location.OUTDOOR);
			log.info("Run: " + run);
		};

	}

}
