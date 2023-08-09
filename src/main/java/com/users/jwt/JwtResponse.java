package com.users.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse <T>{
	
	private T result;
	private String message;
	private Integer status;
	private String token;

}
