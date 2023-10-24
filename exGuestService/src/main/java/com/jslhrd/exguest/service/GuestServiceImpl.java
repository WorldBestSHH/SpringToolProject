package com.jslhrd.exguest.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslhrd.exguest.domain.GuestDTO;
import com.jslhrd.exguest.mapper.GuestMapper;

@Service
public class GuestServiceImpl implements GuestService{
	@Autowired
	private GuestMapper mapper;
	
	@Override
	public int guestCount() {
		return mapper.guestCount();
	}
	
	@Override
	public List<GuestDTO> guestList() {
		return mapper.guestList();
	}

	@Override
	public void guestHits(int idx, HttpServletRequest request, HttpServletResponse response) {
		//쿠키생성
		boolean bool = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies(); //쿠키정보를 배열에 저장
		for(int i=0; i<cookies.length; i++) {
			info = cookies[i];
			if(info.getName().equals("guestCookie"+idx)) {
				bool = true;
				break;
			}
		}
		String str=""+System.currentTimeMillis();
		//쿠키가 없으면 생성
		if(!bool) {
			info = new Cookie("guestCookie"+idx, str);
			info.setMaxAge(60*5); //5분
			response.addCookie(info);
			mapper.guestHits(idx);
		}
		
	}

	@Override
	public GuestDTO guestSearch(int idx) {
		return mapper.guestSearch(idx);
	}

	@Override
	public int guestWrite(GuestDTO dto) {
		// TODO Auto-generated method stub
		return mapper.guestWrite(dto);
	}
	
	@Override
	public int guestModify(GuestDTO dto) {
		// TODO Auto-generated method stub
		return mapper.guestModify(dto);
	}
	
	@Override
	public int guestDelete(int idx) {
		// TODO Auto-generated method stub
		return mapper.guestDelete(idx);
	}
	
}
