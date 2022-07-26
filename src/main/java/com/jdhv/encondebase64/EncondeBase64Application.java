package com.jdhv.encondebase64;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EncondeBase64Application {

	public static void main(String[] args) {
		SpringApplication.run(EncondeBase64Application.class, args);
	}

}
