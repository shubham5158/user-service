package com.users.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.users.entity.User;
import com.users.exception.UserAlreadyExistException;
import com.users.repository.UserRepository;
import com.users.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	public static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(User user) {

		logger.info("UserServiceImpl:rgisterUser():started");

		List<User> users = userRepository.findByEmail(user.getEmail());

		if (users != null && users.size() > 0) {
			throw new UserAlreadyExistException("User Already Exist ");
		}

		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isEmpty()) {

		}
		return optionalUser.get();
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);

	}

	@Override
	public User updateUser(Long userId, User updatedUser) {
		Optional<User> optionalExistingUser = userRepository.findById(userId);

		if (optionalExistingUser.isPresent()) {
			User existingUser = optionalExistingUser.get();

			if (updatedUser.getFirstName() != null) {
				existingUser.setFirstName(updatedUser.getFirstName());
			}

			if (updatedUser.getMiddleName() != null) {
				existingUser.setMiddleName(updatedUser.getMiddleName());
			}

			if (updatedUser.getLastName() != null) {
				existingUser.setLastName(updatedUser.getLastName());
			}

			if (updatedUser.getEmail() != null) {
				existingUser.setEmail(updatedUser.getEmail());
			}

			if (updatedUser.getPassword() != null) {
				existingUser.setPassword(updatedUser.getPassword());
			}

			if (updatedUser.getPhoneNumber() != null) {
				existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
			}

			User savedUser = userRepository.save(existingUser);
			return savedUser;
		} else {
		}
		return updatedUser;
	}

	@Override
	public User login(String email, String password) {
		List<User> users = userRepository.findByEmail(email);

		if (users != null && users.size() > 0 && users.get(0).getPassword().equals(password)) {
			return users.get(0);

		}
		return null;
	}

}
