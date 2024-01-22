package com.vendingmachine.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vendingmachine.entity.BillingDetails;
import com.vendingmachine.entity.Coin;
import com.vendingmachine.entity.Product;
import com.vendingmachine.entity.PurchaseRequest;
import com.vendingmachine.service.VendingService;

@RestController
public class VendingMachineController {
	 
	    @Autowired
	    private VendingService vendingMachineService;

	    
	    @GetMapping("/inventory")
	    public List<Product> getInventory() {
	         List<Product> inventory = vendingMachineService.getInventory();
	         return inventory;
	    }

	    @PostMapping("/purchase")
	    public BillingDetails purchaseProduct(@RequestBody List<PurchaseRequest> purchaseRequest) {
	    	System.out.println(purchaseRequest  +"list of product from customer");
	    	
	      BillingDetails purchaseProduct = vendingMachineService.purchaseProducts(purchaseRequest);
	    
	       return purchaseProduct;
	    }

	    @PostMapping("/update-inventory")
	    public void updateInventory(@RequestBody List<Product> updatedProducts) {
	        vendingMachineService.updateInventory(updatedProducts);
	    }

	    @PostMapping("/update-coins")
	    public void updateCoins(@RequestBody List<Coin> updatedCoins) {
	        vendingMachineService.updateCoinFloat(updatedCoins);
	    }
	
	}




	


