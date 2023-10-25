package com.jslhrd.exuser.domain;

import lombok.Data;

@Data
public class UserDTO {
	private String name;
	private String userid;
	private String passwd;
	private String tel;
	private String email;
	private String first_time;
	private String last_time;
	
}
