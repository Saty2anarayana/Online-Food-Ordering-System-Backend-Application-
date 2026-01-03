package com.satya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.satya.entity.OrderEntity;
import com.satya.entity.UserEntity;
import com.satya.service.OrderService;
import com.satya.service.UserService;


@RestController
@RequestMapping("/admin")
public class AdminOrderController {
	

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/order/restaurant/{id}")
	public ResponseEntity<List<OrderEntity>> getOrderHistory(
			@PathVariable("id") Long id,
			@RequestParam(required=false)String order_status,
			@RequestHeader("Authorization") String jwt )throws Exception{
		UserEntity user=userService.findUserByJwtToken(jwt);
		List<OrderEntity> order=orderService.getRestaurantsOrder(id,order_status);
		    
		 return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@PutMapping("/order/restaurant/{id}/{orderStatus}")
	public ResponseEntity<OrderEntity> updateOrderStatus(
			@PathVariable("id") Long id,
			@PathVariable("orderStatus") String orderStatus,
			@RequestHeader("Authorization") String jwt )throws Exception{
		UserEntity user=userService.findUserByJwtToken(jwt);
		OrderEntity order=orderService.updateOrder(id,orderStatus);
		    
		 return new ResponseEntity<>(order,HttpStatus.OK);
	}

}

