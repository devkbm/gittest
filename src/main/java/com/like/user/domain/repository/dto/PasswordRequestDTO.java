package com.like.user.domain.repository.dto;

import lombok.Data;

@Data
public class PasswordRequestDTO {

	String userId;
	
	String beforePassword;
	
	String afterPassword;	
}
