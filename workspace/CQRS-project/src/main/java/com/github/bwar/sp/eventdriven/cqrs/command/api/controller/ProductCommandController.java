package com.github.bwar.sp.eventdriven.cqrs.command.api.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.bwar.sp.eventdriven.cqrs.command.api.commands.CreateProductCommand;
import com.github.bwar.sp.eventdriven.cqrs.command.api.model.ProductModel;

@RestController
@RequestMapping("/products")
public class ProductCommandController {
	
	private CommandGateway commandGateway;
	
	public ProductCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}
	
	@PostMapping
	public String addProducts(@RequestBody ProductModel productModel) {
		
		CreateProductCommand createProductCommand = CreateProductCommand.builder()
				.productId(UUID.randomUUID().toString())
				.name(productModel.getName())
				.price(productModel.getPrice())
				.quantity(productModel.getQuantity())
				.build();
		
		String result = commandGateway.sendAndWait(createProductCommand);
		return result;
		
	}

}
