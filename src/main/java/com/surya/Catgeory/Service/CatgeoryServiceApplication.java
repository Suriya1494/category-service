package com.surya.Catgeory.Service;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CatgeoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatgeoryServiceApplication.class, args);
	}

	@Bean
	public ModelMapper getMap()
	{
		return new ModelMapper();
	}

}
