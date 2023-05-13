package com.github.bwar.sp.eventdriven.cqrs.command.api.events;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.github.bwar.sp.eventdriven.cqrs.command.api.entity.Product;
import com.github.bwar.sp.eventdriven.cqrs.command.api.repository.ProductRepository;

@Component
@ProcessingGroup("product")
public class ProductEventsHandler {
	
	private ProductRepository productRepository;
	
	public ProductEventsHandler(ProductRepository productRepository) {
		this.productRepository=productRepository;
	}
	
	@EventHandler
	public void on(ProductCreatedEvent event) {
		Product product = new Product();
		BeanUtils.copyProperties(event, product);
		
		productRepository.save(product);
		
		
	}
	@ExceptionHandler
	public void handle(Exception exception) throws Exception {
		throw exception;
	}

}
