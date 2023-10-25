package com.jslhrd.exuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslhrd.exuser.domain.UserDTO;
import com.jslhrd.exuser.mapper.UserMapper;
import com.jslhrd.exuser.util.UserSha256;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;
	
	@Override
	public int userIDCheck(String userid) {
		return mapper.userIDCheck(userid);
	}
	
/*	
    //비번 암호화 컨트롤러에서 처리 
	@Override
	public int userInsert(UserDTO dto) {
		return mapper.userInsert(dto);
	}
*/
	
	@Override
	public int userInsert(UserDTO dto) {
		String encryPassword = UserSha256.encrypt(dto.getPasswd());
		dto.setPasswd(encryPassword);

		return mapper.userInsert(dto);
	}
	
}
