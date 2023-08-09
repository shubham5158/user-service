package com.users.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseClass {

	private Object result;
	private String message;
	private String status;

}
