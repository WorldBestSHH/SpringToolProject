package com.jslhrd.expds.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jslhrd.expds.domain.PdsDTO;

public interface PdsService {
	//총 게시글 카운트
	public int pdsCount();
	//게시글 전체 목록
	public List<PdsDTO> pdsList();
	//게시글 등록
	public int pdsWrite(PdsDTO dto);
	//특정글 조회수 증가(View)
	public void pdsHits(int idx, HttpServletRequest request, HttpServletResponse response);
	//idx 해당 글 검색
	public PdsDTO pdsSearch(int idx);
	//게시글 수정
	public int pdsModify(PdsDTO dto);	
	//게시글 삭제
	public int pdsDelete(int idx, String pass);
}
