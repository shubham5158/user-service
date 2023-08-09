package com.users.exception;

public class UserAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = -5511002882185703183L;

	public UserAlreadyExistException(String message) {
		super(message);
	}

}
