package com.willian.AlpacaFilmes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlpacaFilmesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlpacaFilmesApplication.class, args);
	}

}
