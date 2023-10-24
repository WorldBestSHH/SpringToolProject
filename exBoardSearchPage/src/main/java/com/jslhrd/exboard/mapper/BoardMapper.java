package com.jslhrd.exboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jslhrd.exboard.domain.BoardDTO;
import com.jslhrd.exboard.domain.PageDTO;

@Mapper
public interface BoardMapper {
	//총 게시글 카운트
	public int boardCount();
/*
	//게시글 전체 목록(페이지x, 검색x)
	public List<BoardDTO> boardList();
*/	
	//게시글 전체 목록(페이지o, 검색x)
	public List<BoardDTO> boardList(PageDTO dto);
	//게시글 등록
	public int boardWrite(BoardDTO dto);
	//특정글 조회수 증가(View)
	public void boardHits(int idx);
	//idx 해당 글 검색
	public BoardDTO boardSearch(int idx);
	//게시글 수정
	public int boardModify(BoardDTO dto);
	//게시글 삭제
	public int boardDelete(int idx, String pass);
}
