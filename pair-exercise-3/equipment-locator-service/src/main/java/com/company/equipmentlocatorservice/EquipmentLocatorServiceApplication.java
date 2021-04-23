package com.company.equipmentlocatorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EquipmentLocatorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquipmentLocatorServiceApplication.class, args);
	}

}
