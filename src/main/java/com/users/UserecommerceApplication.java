package com.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserecommerceApplication {

	public static Logger logger = LoggerFactory.getLogger(UserecommerceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UserecommerceApplication.class, args);
		logger.info("******User Service Started Successfully******");

	}

}
