package com.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {

	private Object result;
	private String message;
	private String status;

}
