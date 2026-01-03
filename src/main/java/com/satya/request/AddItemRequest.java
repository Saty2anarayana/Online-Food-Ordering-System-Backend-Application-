package com.satya.request;

import java.util.List;

import lombok.Data;

@Data
public class AddItemRequest {

	private Long fooId;
	private int quantity;
	
	private List<String> ingredients;
}
