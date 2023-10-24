package com.jslhrd.exboard.domain;

import lombok.Data;

@Data
public class BoardDTO {
	private int idx;
	private String name;
	private String email;
	private String pass;
	private String subject;
	private String contents;
	private String regdate;
	private int readcnt;
}
