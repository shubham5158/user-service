package com.users.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.users.entity.Role;
import com.users.repository.RoleRepository;
import com.users.service.RoleService;

public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role findByRole(String role) {
		return roleRepository.findByRole(role);
	}

	@Override
	public Role findById(Long id) {
		return roleRepository.findById(id).get();
	}

}
