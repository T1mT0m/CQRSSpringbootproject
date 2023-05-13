package com.github.bwar.sp.eventdriven.cqrs.query.api.controller;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.bwar.sp.eventdriven.cqrs.command.api.model.ProductModel;
import com.github.bwar.sp.eventdriven.cqrs.query.api.queries.GetProductsQuery;

@RestController
@RequestMapping("/products")
public class ProductQueryController {
	
	private QueryGateway queryGateway;
	
	public ProductQueryController(QueryGateway queryGateway) {
		this.queryGateway= queryGateway;
	}
	
	@GetMapping
	public List<ProductModel> getAllProducts(){
		
		GetProductsQuery getProductsQuery=new GetProductsQuery();
		List<ProductModel> productModel = queryGateway.query(
				getProductsQuery,
				ResponseTypes.multipleInstancesOf(ProductModel.class))
				.join();
		
		return productModel;
	}

}
