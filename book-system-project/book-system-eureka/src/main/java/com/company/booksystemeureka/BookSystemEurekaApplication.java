package com.company.booksystemeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BookSystemEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookSystemEurekaApplication.class, args);
	}

}
