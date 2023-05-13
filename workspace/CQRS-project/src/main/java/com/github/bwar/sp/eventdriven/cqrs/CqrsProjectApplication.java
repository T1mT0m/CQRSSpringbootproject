package com.github.bwar.sp.eventdriven.cqrs;

import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.bwar.sp.eventdriven.cqrs.command.api.exception.ProductsServiceEventsErrorHandler;

@SpringBootApplication
public class CqrsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CqrsProjectApplication.class, args);
	}
	
	public void configure(EventProcessingConfigurer configurer) {
		configurer.registerListenerInvocationErrorHandler(
				"product", 
				configuration-> new ProductsServiceEventsErrorHandler());
	}
}
