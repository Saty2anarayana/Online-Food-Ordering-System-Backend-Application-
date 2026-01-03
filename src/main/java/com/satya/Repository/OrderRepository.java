package com.satya.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satya.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
	
	public List<OrderEntity> findByCustomerId(Long userId);
	
	public List<OrderEntity> findByRestaurantId(Long restaurantId);

}
