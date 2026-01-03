package com.satya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.satya.entity.AddressEntity;

public interface AddressRepository extends JpaRepository <AddressEntity,Long> {

}
