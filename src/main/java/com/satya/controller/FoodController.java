package com.satya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.satya.entity.Food;
import com.satya.entity.Restaurant;
import com.satya.entity.UserEntity;
import com.satya.request.CreateFoodRequest;
import com.satya.service.FoodService;
import com.satya.service.RestaurantService;
import com.satya.service.UserService;

@RestController
@RequestMapping("/api/food")
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Food>> searchFood(@RequestParam String name,
			                       @RequestHeader("Authorization")String jwt)throws Exception{
		UserEntity user=userService.findUserByJwtToken(jwt);
		
		List<Food> food=foodService.SearchFood(name);
		
		return new ResponseEntity<>(food,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam boolean vegetarian,
			@RequestParam boolean seasonal,
			@RequestParam boolean nonveg,
			@RequestParam(required=false) String food_category,
			@PathVariable("id") Long restaurantId,
			@RequestHeader("Authorization")String jwt)throws Exception{
		UserEntity user=userService.findUserByJwtToken(jwt);
		
		List<Food> food=foodService.getRestaurantsFood(restaurantId, vegetarian, nonveg, seasonal, food_category);
		
		return new ResponseEntity<>(food,HttpStatus.OK);
		
	}

}
