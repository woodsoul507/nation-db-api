package me.givo.nationdbapiproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NationDbApiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(NationDbApiProjectApplication.class, args);
	}

}
