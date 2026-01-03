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

import com.satya.dto.RestaurantDto;
import com.satya.entity.Restaurant;
import com.satya.entity.UserEntity;
import com.satya.service.RestaurantService;
import com.satya.service.UserService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Restaurant>> searchRestaurant(
			@RequestHeader("Authorization") String jwt,@RequestParam String keyword) throws Exception{
		
		UserEntity user=userService.findUserByJwtToken(jwt);
		
		List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);
		
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<Restaurant>> getAllRestaurant(
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		UserEntity user=userService.findUserByJwtToken(jwt);
		
		List<Restaurant> restaurant = restaurantService.getAllRestaurant();
		
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> findRestaurantById(
			@RequestHeader("Authorization") String jwt,@PathVariable("id") Long id) throws Exception{
		
		UserEntity user=userService.findUserByJwtToken(jwt);
		
		Restaurant restaurant = restaurantService.findRestaurantById(id);
		
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
	}
	
	@PutMapping("{id}/add-favorites")
	public ResponseEntity<RestaurantDto> addToFavorites(
			@RequestHeader("Authorization") String jwt,@PathVariable("id") Long id) throws Exception{
		UserEntity user=userService.findUserByJwtToken(jwt);
		
		RestaurantDto restaurant = restaurantService.addToFavorites(id,user);
		
		return new ResponseEntity<>(restaurant,HttpStatus.OK);
	}
 	
	
	
	
	

}
