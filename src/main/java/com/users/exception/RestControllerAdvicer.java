package com.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.users.dto.ResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestControllerAdvicer extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EmptyFieldException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ResponseClass handleException(final EmptyFieldException exception,
			final HttpServletRequest request) {
		ResponseClass error = new ResponseClass();
		error.setMessage(exception.getMessage());
		error.setStatus("failed");
		return error;
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ResponseClass handleException(final UserNotFoundException exception,
			final HttpServletRequest request) {
		ResponseClass error = new ResponseClass();
		error.setMessage(exception.getMessage());
		error.setStatus("failed");
		return error;
	}

	@ExceptionHandler(UserAlreadyExistException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody ResponseEntity<ResponseDTO> userAlreadyExistException(
			final UserAlreadyExistException exception, final HttpServletRequest request) {
		ResponseDTO error = new ResponseDTO();
		error.setMessage(exception.getMessage());
		error.setStatus("failed");
		return ResponseEntity.badRequest().body(error);
	}

}
