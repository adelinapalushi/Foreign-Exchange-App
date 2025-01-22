package com.forexapp.forexapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ForexapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForexapplicationApplication.class, args);
	}

}
