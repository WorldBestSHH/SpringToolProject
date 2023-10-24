package com.example.extest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.extest.domain.TestDTO;

@Controller
public class TestController {
	//실제 배포할 때 서버에만 남아서 사용자는 안 보임
	private static final Logger log = LoggerFactory.getLogger(TestController.class);
	
	@GetMapping("")
	public String index() {
		return "/index";
	}
	
	@GetMapping("/index")
	public void index2() {
		//return 타입이 void 이면 @GetMapping("/index")가 파일
	}
	
	@GetMapping("/test")
	public void test(//@RequestParam("idx") int idx, @RequestParam("page") int page,
					@ModelAttribute("idx") int idx, @ModelAttribute("page") int page) { 
		//RequestParam은 넘어온 값. 넘어온 값을 새로운 값으로 만들 때 사용
		//ModelAttribute은 전달자
		//받은걸 그대로 전달할 때 변수명이 같으면 ModelAttribute만 있어도 됨 
		log.info("idx = "+idx);
		log.info("page = "+page);
	}
	
	@GetMapping("/test2")
	public void test2(int idx, int page) { 
		//똑같은 이름이 있으면 자동으로 대입됨. 이름이 다르면 넘어오지 않음
		log.info("idx = " + idx);
		log.info("page = " + page);
	}
	
	@GetMapping("/test3")
	public void test3(TestDTO dto) { 
		//dto는 자동으로 수집됨
		log.info("dto = " + dto);
	}
}
