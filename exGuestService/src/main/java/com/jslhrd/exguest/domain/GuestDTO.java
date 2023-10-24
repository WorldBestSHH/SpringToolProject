package com.jslhrd.exguest.domain;

import lombok.Data;

@Data
public class GuestDTO {
	private int idx;
	private String name;
	private String subject;
	private String contents;
	private String regdate;
	private int readcnt;
}
