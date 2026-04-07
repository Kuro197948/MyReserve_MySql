package com.example.app.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AdminLoginForm {
	
	
	@NotBlank
	private String loginId;
	private String loginPass;
}
