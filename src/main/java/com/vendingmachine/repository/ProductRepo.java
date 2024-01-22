package com.vendingmachine.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendingmachine.entity.Coin;
import com.vendingmachine.entity.Product;


public interface ProductRepo extends JpaRepository<Product, Long> {

	

}
