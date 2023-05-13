package com.github.bwar.sp.eventdriven.cqrs.command.api.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Product {
	@Id
	private String productId;
	private String name;
	private BigDecimal price;
	private Integer quantity;

}
