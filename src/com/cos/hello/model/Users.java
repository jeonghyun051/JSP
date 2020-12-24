package com.cos.hello.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Users {

	private int id;	//Primary key, auto_incremenet
	private String username;
	private String password;
	private String email;
	
	
	
}
