package com.jslhrd.exmember.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jslhrd.exmember.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	//전체 목록
	public List<MemberDTO> memberList();
	//회원가입
	public int memberInsert(MemberDTO dto);
	//회원정보
	public MemberDTO memberSelect(String userid);
	//회원수정
	public int memberModify(MemberDTO dto);
}
