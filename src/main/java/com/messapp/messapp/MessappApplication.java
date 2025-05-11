package com.messapp.messapp;

import org.springframework.boot.SpringApplication;  

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.messapp.messapp.middleware.EnvLoader;

@SpringBootApplication
public class MessappApplication {

	public static void main(String[] args) {
		
		new EnvLoader();
		SpringApplication.run(MessappApplication.class, args);
	}

}
