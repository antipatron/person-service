package com.fakecompany.micro.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PersonserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonserviceApplication.class, args);
	}

}
