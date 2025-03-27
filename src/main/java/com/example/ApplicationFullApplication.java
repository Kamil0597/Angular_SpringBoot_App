package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.example.BookCategoryPart",
		"com.example.ReaderPart",
		"com.example.Gateway"
})
public class ApplicationFullApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ApplicationFullApplication.class, args);
	}
}