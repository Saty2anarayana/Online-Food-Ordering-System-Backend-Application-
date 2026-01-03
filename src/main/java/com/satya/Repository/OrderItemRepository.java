package com.satya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satya.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long>{

}
