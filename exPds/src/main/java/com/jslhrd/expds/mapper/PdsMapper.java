package com.jslhrd.expds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jslhrd.expds.domain.PdsDTO;

@Mapper
public interface PdsMapper {
	//총 게시글 카운트
	public int pdsCount();
	//게시글 전체 목록
	public List<PdsDTO> pdsList();
	//게시글 등록
	public int pdsWrite(PdsDTO dto);
	//특정글 조회수 증가(View)
	public void pdsHits(int idx);
	//idx 해당 글 검색
	public PdsDTO pdsSearch(int idx);
	//게시글 수정
	public int pdsModify(PdsDTO dto);
	//게시글 삭제
	public int pdsDelete(int idx, String pass);
}
