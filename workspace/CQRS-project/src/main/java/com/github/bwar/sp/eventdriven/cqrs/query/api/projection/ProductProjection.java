package com.github.bwar.sp.eventdriven.cqrs.query.api.projection;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.github.bwar.sp.eventdriven.cqrs.command.api.entity.Product;
import com.github.bwar.sp.eventdriven.cqrs.command.api.model.ProductModel;
import com.github.bwar.sp.eventdriven.cqrs.command.api.repository.ProductRepository;
import com.github.bwar.sp.eventdriven.cqrs.query.api.queries.GetProductsQuery;

@Component
public class ProductProjection {

	private ProductRepository productRepository;
	
	public ProductProjection(ProductRepository productRepository) {
		this.productRepository=productRepository;
	}
	
	@QueryHandler
	public List<ProductModel> handle(GetProductsQuery getProductsQuery){
		
		List<Product> products = productRepository.findAll();
		List<ProductModel> productModel= products.stream()
				.map(product -> ProductModel
						.builder()
						.quantity(product.getQuantity())
						.price(product.getPrice())
						.name(product.getName())
						.build())
				.collect(Collectors.toList());
		
		return productModel;
		
	}
}
