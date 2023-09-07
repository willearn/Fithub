package com.fithub.model.javamail;

import lombok.Data;

//聯絡我們 表單

@Data
public class FormData {

	private String name;
	private String phone;
	private String email;
	private String message;
	private String subject;
}
