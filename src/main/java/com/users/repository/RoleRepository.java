package com.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.users.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("SELECT r FROM Role r where r.role=?1")
	Role findByRole(String role);

}
