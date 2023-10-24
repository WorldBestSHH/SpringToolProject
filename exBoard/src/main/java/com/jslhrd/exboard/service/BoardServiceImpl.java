package com.jslhrd.exboard.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslhrd.exboard.domain.BoardDTO;
import com.jslhrd.exboard.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardMapper mapper;
	
	@Override
	public int boardCount() {
		return mapper.boardCount();
	}
	
	@Override
	public List<BoardDTO> boardList(){
		return mapper.boardList();
	}
	
	@Override
	public int boardWrite(BoardDTO dto) {
		return mapper.boardWrite(dto);
	}
	
	@Override
	public void boardHits(int idx, HttpServletRequest request, HttpServletResponse response) {
		boolean bool = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();
		for(int i=0; i<cookies.length; i++) {
			info = cookies[i];
			if(info.getName().equals("boardCookie" + idx)) {
				bool = true;
				break;
			}
		}
		String str=""+System.currentTimeMillis();
		if(!bool) {
			info = new Cookie("boardCookie" + idx, str);
			info.setMaxAge(60*5);
			response.addCookie(info);
			mapper.boardHits(idx);
		}
	}
	
	@Override
	public BoardDTO boardSearch(int idx) {
		return mapper.boardSearch(idx);
	}
	
	@Override
	public int boardModify(BoardDTO dto) {
		return mapper.boardModify(dto);
	}
	
	@Override
	public int boardDelete(int idx, String pass) {
		return mapper.boardDelete(idx, pass);
	}
}
