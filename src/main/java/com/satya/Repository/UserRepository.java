package com.satya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satya.entity.UserEntity;

public interface UserRepository extends JpaRepository <UserEntity,Long> {
	
	public UserEntity findByEmail(String email);

}
