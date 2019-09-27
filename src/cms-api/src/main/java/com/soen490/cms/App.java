package com.soen490.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class App {

	public static void main(String[] args) {
		log.info("Running CMS app");
		SpringApplication.run(App.class, args);
	}

}
