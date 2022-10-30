package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EntityScan("com.example.demo")
public class DocumentApiRestApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DocumentApiRestApplication.class, args);
	}
}
