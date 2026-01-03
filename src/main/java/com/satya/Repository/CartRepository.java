package com.satya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satya.entity.Cart;

public interface CartRepository extends JpaRepository <Cart,Long> {
	
	public Cart findByCustomerId(Long userId);

}
