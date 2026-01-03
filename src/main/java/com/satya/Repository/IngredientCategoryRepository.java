package com.satya.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satya.entity.IngredientCategory;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory,Long> {
	
	List<IngredientCategory> findByRestaurantId(Long id);

}
