package com.jslhrd.exuser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jslhrd.exuser.domain.UserDTO;

@Mapper
public interface UserMapper {
	//아이디 중복검사
	public int userIDCheck(String userid);
	//회원가입처리
	public int userInsert(UserDTO dto);
	//로그인 시 아이디를 이용하느 비번 검색
	public String userIDPassSearch(UserDTO dto);
	//로그인(ID, Pass) 성공시 사용자 정보 반환
	public UserDTO userLogin(UserDTO dto);
	//로그인 성공시 최근 접속일자 업데이트
	public void userLoginUpdate(UserDTO dto);
	//회원목록
	public List<UserDTO> userList();
	//회원정보수정처리
	public int userModify(UserDTO dto);
}
