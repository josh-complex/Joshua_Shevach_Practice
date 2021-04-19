package com.trilogyed.tasker;

import com.trilogyed.tasker.model.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TaskerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskerServiceApplication.class, args);
	}

}
