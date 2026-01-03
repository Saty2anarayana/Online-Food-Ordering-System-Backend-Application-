package com.satya.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satya.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
	
	public List<Category> findByRestaurantId(Long id);

}
