package com.banking.cards;

import com.banking.cards.dto.CardsContactInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.eazybytes.cards.controller") })
@EnableJpaRepositories("com.eazybytes.cards.repository")
@EntityScan("com.eazybytes.cards.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
	info = @Info(
		title = "Cards microservice REST API Documentation",
		description = "Cards microservice REST API Documentation",
		version = "v1",
		contact = @Contact(
			name = "Madan Reddy",
			email = "tutor@yahoo.com",
			url = "https://www.yahoo.com"
		),
		license = @License(
			name = "Apache 2.0",
			url = "https://www.yahoo.com"
		)
	),
	externalDocs = @ExternalDocumentation(
		description = "Cards microservice REST API Documentation",
		url = "https://www.yahoo.com/swagger-ui.html"
	)
)
@EnableConfigurationProperties(value = { CardsContactInfoDTO.class })
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}
}
