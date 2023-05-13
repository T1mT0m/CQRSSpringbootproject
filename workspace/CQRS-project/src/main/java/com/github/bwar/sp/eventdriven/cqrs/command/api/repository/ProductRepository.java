package com.github.bwar.sp.eventdriven.cqrs.command.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.bwar.sp.eventdriven.cqrs.command.api.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

}
