package com.example.PrestamosMonetarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PrestamosMonetariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrestamosMonetariosApplication.class, args);
	}

	@Bean
	public RestTemplate getresttemplate() {
		return new RestTemplate();
	}

}
