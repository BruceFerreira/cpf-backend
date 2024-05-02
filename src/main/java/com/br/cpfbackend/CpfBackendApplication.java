package com.br.cpfbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class CpfBackendApplication {

	public static void main(String[] args) {

		SpringApplication.run(CpfBackendApplication.class, args);
	}

}
