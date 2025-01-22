package com.forexapp.forexapplication;

import org.springframework.boot.SpringApplication;

public class TestForexapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.from(ForexapplicationApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
