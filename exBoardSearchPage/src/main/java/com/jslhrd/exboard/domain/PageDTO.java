package com.jslhrd.exboard.domain;

import lombok.Data;

@Data
public class PageDTO {
	//검색
	private String search;
	private String key;
	
	//페이지처리
	private int startpage;
	private int endpage;
}
