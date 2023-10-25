package com.jslhrd.exuser.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jslhrd.exuser.domain.UserDTO;

@Mapper
public interface UserMapper {
	//아이디 중복검사
	public int userIDCheck(String userid);
	
	//회원가입처리
	public int userInsert(UserDTO dto);



}
