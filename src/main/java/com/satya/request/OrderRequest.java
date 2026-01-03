package com.satya.request;

import com.satya.entity.AddressEntity;

import lombok.Data;

@Data
public class OrderRequest {
	
	private Long restaurantId;
	private AddressEntity deliveryAddress;

}
