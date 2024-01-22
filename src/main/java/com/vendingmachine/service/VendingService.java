package com.vendingmachine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendingmachine.entity.BillingDetails;
import com.vendingmachine.entity.Coin;
import com.vendingmachine.entity.Product;
import com.vendingmachine.entity.PurchaseRequest;
import com.vendingmachine.repository.CoinRepo;
import com.vendingmachine.repository.ProductRepo;


@Service
public class VendingService {
	
	
   

    @Autowired
    private  ProductRepo productRepository;
    @Autowired
    private  CoinRepo coinRepository;
    
    private int orderCounter = 0;
    public List<Product> getInventory() {
        return productRepository.findAll();
    }

    
    
   
    public  BillingDetails purchaseProducts(List<PurchaseRequest> purchaseRequests) {
        List<Product> purchasedProducts = new ArrayList<>();
        BillingDetails bill = new BillingDetails();

        for (PurchaseRequest purchaseRequest : purchaseRequests) {
            // Fetch the product and validate its existence
            Optional<Product> productOptional = productRepository.findById(purchaseRequest.getProductId());
            if (productOptional.isEmpty()) {
                throw new RuntimeException("Product not found with ID: " + purchaseRequest.getProductId());
            }

            Product product = productOptional.get();
           
            int totalPrice = product.getPrice();

          
            int totalCoins = purchaseRequest.getCoinValues().stream().mapToInt(Integer::intValue).sum();
            System.out.println(totalCoins+"coins from customer");
            if (totalCoins < totalPrice) {
                throw new RuntimeException("Insufficient funds. Please insert more coins.");
            }

            // Validate the individual coin denominations
            validateCoins(purchaseRequest.getCoinValues());

            // Update product quantity
            int remainingQuantity = product.getQuantity() - 1;
            if (remainingQuantity < 0) {
                throw new RuntimeException("Product is out of stock.");
            }
            product.setQuantity(remainingQuantity);

            // Calculate and return change
            int change = totalCoins - totalPrice;

            
            productRepository.save(product);
            orderCounter++;
            
            Product purchasedProduct = new Product();
            purchasedProduct.setId(product.getId());
            purchasedProduct.setName(product.getName());
            purchasedProduct.setPrice(product.getPrice());
            purchasedProduct.setQuantity(1);        // Always 1 for simplicity
            purchasedProducts.add(purchasedProduct);
           
           
            
            bill.setProduct(purchasedProducts);
            bill.setMoneyreturned(change);
            bill.setTotalmoney(totalCoins);
            bill.setTotalprice(totalPrice);
        }

        return bill;
    }

    
    
    
    
    
    private void validateCoins(List<Integer> coinValues) {
        for (Integer coinValue : coinValues) {
            if (!isValidCoin(coinValue)) {
                throw new RuntimeException("Invalid coin denomination: " + coinValue);
            }
        }
    }

    private boolean isValidCoin(int coinValue) {
     
        return Set.of(1, 5, 10, 25, 50, 100).contains(coinValue);
    }

   
    public void updateInventory(List<Product> updatedProducts) {
        productRepository.saveAll(updatedProducts);
    }

    
    public void updateCoinFloat(List<Coin> updatedCoins) {
        coinRepository.saveAll(updatedCoins);
    }
    public int getOrderCounter() {
        return orderCounter;
    }
		
}
