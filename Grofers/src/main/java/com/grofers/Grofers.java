package com.grofers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Grofers {

	public static void main(String[] args) {
		SpringApplication.run(Grofers.class, args);
	}
	
	@PostConstruct
    public void init() {
        System.out.println("Inside init");
    }

}
