package com.vendingmachine.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class PurchaseRequest {
	
	  private List<Integer> coinValues;
	    private Long productId;

	    // Constructors, getters, and setters

	    public List<Integer> getCoinValues() {
	        return coinValues;
	    }

	    public void setCoinValues(List<Integer> coinValues) {
	        this.coinValues = coinValues;
	    }

	    public Long getProductId() {
	        return productId;


	    }
}
