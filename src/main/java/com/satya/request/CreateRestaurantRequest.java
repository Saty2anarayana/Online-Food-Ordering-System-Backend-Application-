package com.satya.request;

import java.util.List;

import com.satya.entity.AddressEntity;
import com.satya.entity.ContactInformation;

import lombok.Data;


@Data
public class CreateRestaurantRequest {
	
	private Long id;
	private String name;
	private String description;
	private String cuisineType;
	private AddressEntity address;
	private ContactInformation contactInformation;
	private String openingHours;
	private List<String> images;

}
