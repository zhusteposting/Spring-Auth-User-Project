package com.eposting.epost;

import com.eposting.epost.configuration.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class EpostApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpostApplication.class, args);
	}

}
