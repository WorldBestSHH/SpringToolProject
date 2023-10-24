package com.jslhrd.exExam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExamController {
	@GetMapping("")
	public String indexTest() {
		return "index";
	}
	
	@GetMapping("/test")
	public String linkTest() {
		return "test";
	}
}
