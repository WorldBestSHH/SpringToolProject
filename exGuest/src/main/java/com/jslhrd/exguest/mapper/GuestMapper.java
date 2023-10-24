package com.jslhrd.exguest.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jslhrd.exguest.domain.GuestDTO;

@Mapper
public interface GuestMapper {
	//총 게시글 수
	public int guestCount();
	//게시글 전체 목록
	public List<GuestDTO> guestList();
	//게시글 등록
	public int guestWrite(GuestDTO dto);
	//idx에 해당하는 글 검색(view에서 사용)
	public GuestDTO guestSearch(int idx);
	//게시글 수정
	public int guestModify(GuestDTO dto);
	//게시글 삭제
	public int guestDelete(int idx);
}
