package com.satya.request;

import lombok.Data;

@Data
public class ingredientRequest {

	
	private String name;
	private Long categoryId;
	private Long restaurantId;
}
