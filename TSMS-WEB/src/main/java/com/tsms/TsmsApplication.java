package com.tsms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TsmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TsmsApplication.class, args);
	}
	
	@Bean
	ModelMapper modelmapper() {
		return new ModelMapper();
	}

	@Bean
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
}
