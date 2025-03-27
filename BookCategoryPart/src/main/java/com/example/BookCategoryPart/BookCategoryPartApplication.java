package com.example.BookCategoryPart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BookCategoryPartApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookCategoryPartApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(@Value("${reader.part.url}") String baseUrl) {
		return new RestTemplateBuilder().rootUri(baseUrl).build();
	}

}
