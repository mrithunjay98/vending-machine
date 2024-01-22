package com.vendingmachine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendingmachine.entity.Coin;

public interface CoinRepo extends JpaRepository<Coin, Long> {

}
