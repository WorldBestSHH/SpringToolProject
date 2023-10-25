package com.jslhrd.exuser.service;

import com.jslhrd.exuser.domain.UserDTO;

public interface UserService {
	//아이디 중복검사
	public int userIDCheck(String userid);
	
	//회원가입처리
	public int userInsert(UserDTO dto);

}
