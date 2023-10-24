package com.jslhrd.exguest.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.exguest.domain.GuestDTO;

public interface GuestService {
	//전체 게시글 수 카운트
	public int guestCount();

	//전체 글 목록
	public List<GuestDTO> guestList();
	
	//조회수 증가
	public void guestHits(int idx, HttpServletRequest request, HttpServletResponse response);
		
	//idx에 해당하는 글 검색(view에서 사용)
	public GuestDTO guestSearch(int idx);

	//글 등록
	public int guestWrite(GuestDTO dto);

	//수정처리
	public int guestModify(GuestDTO dto);
	
	//삭제
	public int guestDelete(int idx);
	
	
}
