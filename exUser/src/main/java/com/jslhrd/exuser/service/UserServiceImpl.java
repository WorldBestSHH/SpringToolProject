package com.jslhrd.exuser.service;

import java.util.List;

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
	
	@Override
	public String userIDPassSearch(UserDTO dto) {
		return mapper.userIDPassSearch(dto);
	}
	
	@Override
	public UserDTO userLogin(UserDTO dto) {
		return mapper.userLogin(dto);
	}
	
	@Override
	public void userLoginUpdate(UserDTO dto) {
		mapper.userLoginUpdate(dto);
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
	
	@Override
	public List<UserDTO> userList() {
		return mapper.userList();
	}
	
	@Override
	public int userModify(UserDTO dto) {
		String encryPassword = UserSha256.encrypt(dto.getPasswd());
		dto.setPasswd(encryPassword);
		return mapper.userModify(dto);
	}
	
}
