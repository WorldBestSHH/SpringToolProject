package com.jslhrd.expds.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslhrd.expds.domain.PdsDTO;
import com.jslhrd.expds.mapper.PdsMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PdsServiceImpl implements PdsService {
	@Autowired
	private PdsMapper mapper;
	
	@Override
	public int pdsCount() {
		return mapper.pdsCount();
	}
	
	@Override
	public List<PdsDTO> pdsList() {
		return mapper.pdsList();
	}
	
	@Override
	public int pdsWrite(PdsDTO dto) {
		return mapper.pdsWrite(dto);
	}
	
	@Override
	public void pdsHits(int idx, HttpServletRequest request, HttpServletResponse response) {
		boolean bool = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();
		for(int i=0; i<cookies.length; i++) {
			info = cookies[i];
			if(info.getName().equals("pdsCookie" + idx)) {
				bool = true;
				break;
			}
		}
		String str=""+System.currentTimeMillis();
		if(!bool) {
			info = new Cookie("pdsCookie" + idx, str);
			info.setMaxAge(60*5);
			response.addCookie(info);
			mapper.pdsHits(idx);
		}
	}
	
	@Override
	public PdsDTO pdsSearch(int idx) {
		return mapper.pdsSearch(idx);
	}
	
	@Override
	public int pdsModify(PdsDTO dto) {
		return mapper.pdsModify(dto);
	}
	
	@Override
	public int pdsDelete(int idx, String pass) {
		return mapper.pdsDelete(idx, pass);
	}
}
