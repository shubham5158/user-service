package com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.users.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	

}
