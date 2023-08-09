package com.users.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.users.dto.LoginDTO;
import com.users.dto.ResponseDTO;
import com.users.entity.User;
import com.users.exception.UserAlreadyExistException;
import com.users.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	public static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	// User Register controller
	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody User user) {

		logger.info("UserController:registerUser():started");

		ResponseDTO response = new ResponseDTO();

		try {
			User createdUser = userService.registerUser(user);

			if (createdUser != null) {
				response.setResult(createdUser);
				response.setMessage("Successfully Register User");
				response.setStatus("success");
				return ResponseEntity.ok(response);
			} else {
				response.setMessage("Failed.... Try Again");
				response.setStatus("failed");
				return ResponseEntity.badRequest().body(response);
			}
		} catch (UserAlreadyExistException e) {
			response.setMessage("User Already Exit");
			response.setStatus("failed");
			return ResponseEntity.badRequest().body(response);

		} catch (Exception e) {
			response.setMessage("Failed.... Try Again");
			response.setStatus("Failed");
			return ResponseEntity.badRequest().body(response);
		}

	}

	// login user
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO user) {

		logger.info("UserController:login():started");

		ResponseDTO response = new ResponseDTO();

		User createdUser = userService.login(user.getEmail(), user.getPassword());

		if (createdUser != null) {
			response.setResult(createdUser);
			response.setMessage("Successfully Login User");
			response.setStatus("Success");
			return ResponseEntity.ok(response);

		} else {
			response.setMessage("Email And Password Incorrect");
			response.setStatus("Failed");
			return ResponseEntity.badRequest().body(response);
		}

	}

	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
		User user = userService.getUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		List<User> user = userService.getAllUser();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

//	@PutMapping("/{userId}")
//	public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
//		User savedUser = userService.updateUser(userId, updatedUser);
//
//		if (savedUser != null) {
//			return ResponseEntity.ok(savedUser);
//		} else {
//			return ResponseEntity.notFound().build();
//		}
//	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
		try {
			User savedUser = userService.updateUser(userId, updatedUser);
			return new ResponseEntity<>(savedUser, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<String>("User successfully deleted!", HttpStatus.OK);
	}

}
