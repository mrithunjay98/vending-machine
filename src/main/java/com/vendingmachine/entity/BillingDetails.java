package com.vendingmachine.entity;

import java.util.List;

import jakarta.persistence.Entity;

public class BillingDetails {
   List<Product> product;
    private int totalmoney;
    private int totalprice;
    private int moneyreturned;
	
	public int getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(int totalmoney) {
		this.totalmoney = totalmoney;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public int getMoneyreturned() {
		return moneyreturned;
	}
	public void setMoneyreturned(int moneyreturned) {
		this.moneyreturned = moneyreturned;
	}
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
    
    
    
}
