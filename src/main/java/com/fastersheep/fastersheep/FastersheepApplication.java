package com.fastersheep.fastersheep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;



@SpringBootApplication
@PropertySource("custom.properties")
public class FastersheepApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastersheepApplication.class, args);


	}



}
