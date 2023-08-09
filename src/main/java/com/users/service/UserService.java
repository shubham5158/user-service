package com.users.service;

import java.util.List;

import com.users.entity.User;

public interface UserService {

	User getUserById(Long userId);

	List<User> getAllUser();

	User updateUser(Long userId, User updatedUser);

//	User updateUser1(Long userId, User newUser);

	void deleteUser(Long userId);

	User registerUser(User user);

	User login(String email, String password);

}
