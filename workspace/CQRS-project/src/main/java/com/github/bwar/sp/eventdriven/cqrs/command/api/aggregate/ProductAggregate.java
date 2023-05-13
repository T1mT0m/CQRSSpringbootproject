package com.github.bwar.sp.eventdriven.cqrs.command.api.aggregate;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.github.bwar.sp.eventdriven.cqrs.command.api.commands.CreateProductCommand;
import com.github.bwar.sp.eventdriven.cqrs.command.api.events.ProductCreatedEvent;

@Aggregate
public class ProductAggregate {
	
	@AggregateIdentifier
	private String productId;
	private String name;
	private BigDecimal price;
	private Integer quantity;
	
	@CommandHandler
	public ProductAggregate(CreateProductCommand createProductCommand) {
		//perform all the validations and create the event
		ProductCreatedEvent productCreatedEvent = ProductCreatedEvent.builder()
				.productId(createProductCommand.getProductId())
				.name(createProductCommand.getName())
				.price(createProductCommand.getPrice())
				.quantity(createProductCommand.getQuantity())
				.build();
		AggregateLifecycle.apply(productCreatedEvent);	
		
	}
	
	public ProductAggregate() {}
	
	@EventSourcingHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		
		this.productId=productCreatedEvent.getProductId();
		this.name=productCreatedEvent.getName();
		this.price=productCreatedEvent.getPrice();
		this.quantity=productCreatedEvent.getQuantity();
		
	}
}
