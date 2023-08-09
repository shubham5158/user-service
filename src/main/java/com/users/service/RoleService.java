package com.users.service;

import com.users.entity.Role;

public interface RoleService {

	public Role saveRole(Role role);

	public Role findByRole(String role);

	public Role findById(Long id);

}
