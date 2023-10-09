package com.iat.momentsqa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(
		//exclude = {SecurityAutoConfiguration.class} // disable security
)
public class MomentsqaApplication {
	public static void main(String[] args) {
		SpringApplication.run(MomentsqaApplication.class, args);
	}

}
