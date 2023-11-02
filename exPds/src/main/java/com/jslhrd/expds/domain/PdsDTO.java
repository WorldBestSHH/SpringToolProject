package com.jslhrd.expds.domain;

import lombok.Data;

@Data
public class PdsDTO {
	private int idx;
	private String name;
	private String pass;
	private String email;
	private String subject;
	private String contents;
	private String regdate;
	private int readcnt;
	private String filename;
}
