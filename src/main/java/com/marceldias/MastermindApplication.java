package com.marceldias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MastermindApplication {

	private MastermindApplication() {}

	public static void main(String[] args) {
		SpringApplication.run(MastermindApplication.class, args);
	}
}
