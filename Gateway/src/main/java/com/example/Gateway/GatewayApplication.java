package com.example.Gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@SpringBootApplication
public class GatewayApplication {
	public static void main(String[] args) {SpringApplication.run(GatewayApplication.class, args);}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("redirect-add-reader", r -> r.path("/api/categories/add-reader")
						.filters(f -> f.rewritePath("/api/categories/add-reader", "/api/readers/add-reader"))
						.uri("http://localhost:8080"))
				.route("reader-route", r -> r.path("/api/readers/**")
						.filters(f -> f.filter((exchange, chain) -> {
							System.out.println("Request received for /api/elements: " + exchange.getRequest().getURI());
							return chain.filter(exchange);
						}))
						.uri("http://localhost:8080"))
				.route("category-route", r -> r.path("/api/categories/**")
						.filters(f -> f.filter((exchange, chain) -> {
							System.out.println("Request received for /api/categories: " + exchange.getRequest().getURI());
							return chain.filter(exchange);
						}))
						.uri("http://localhost:8081"))
				.route("reader-notifications", r -> r.path("/api/notifications/**")
						.filters(f -> f.filter((exchange, chain) -> {
							System.out.println("Request received for /api/notifications: " + exchange.getRequest().getURI());
							return chain.filter(exchange);
						}))
						.uri("http://localhost:8080"))
				.build();
	}

	@Bean
	public CorsWebFilter corsWebFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList(
				"http://localhost:4200",
				"http://localhost:4201",
				"http://localhost:8085",
				"http://localhost:80"
		));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return new CorsWebFilter(source);
	}
}