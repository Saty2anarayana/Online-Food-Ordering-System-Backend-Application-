package com.satya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satya.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem,Long>{

}
