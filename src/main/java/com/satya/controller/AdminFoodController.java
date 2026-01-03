package com.satya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satya.entity.Food;
import com.satya.entity.Restaurant;
import com.satya.entity.UserEntity;
import com.satya.request.CreateFoodRequest;
import com.satya.response.MessageResponse;
import com.satya.service.FoodService;
import com.satya.service.RestaurantService;
import com.satya.service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping()
	public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req,
			                       @RequestHeader("Authorization")String jwt)throws Exception{
		UserEntity user=userService.findUserByJwtToken(jwt);
		
		Restaurant restaurant = restaurantService.findRestaurantById(req.getRestaurantId());
		Food food=foodService.createFood(req, req.getCategory(), restaurant);
		
		return new ResponseEntity<>(food,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageResponse> deleteFood(@PathVariable("id") Long id,
			                       @RequestHeader("Authorization")String jwt)throws Exception{
		UserEntity user=userService.findUserByJwtToken(jwt);
		
		foodService.deleteFood(id);
		
		MessageResponse res=new MessageResponse();
		res.setMessage("food deleted successfully");
		
		
		return new ResponseEntity<>(res,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable("id") Long id,
			                       @RequestHeader("Authorization")String jwt)throws Exception{
		UserEntity user=userService.findUserByJwtToken(jwt);
		
	    Food food=foodService.updateAvailibilityStatus(id);
		
		
		return new ResponseEntity<>(food,HttpStatus.CREATED);
		
	}

}
