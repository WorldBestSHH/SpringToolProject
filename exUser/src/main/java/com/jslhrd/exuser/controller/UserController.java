package com.jslhrd.exuser.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jslhrd.exuser.domain.UserDTO;
import com.jslhrd.exuser.service.UserService;
import com.jslhrd.exuser.util.UserSha256;

@Controller
@RequestMapping("User")
public class UserController {
	private Logger log = 
			LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;
	
	// 로그인 폼(세션유무 체크)
	@GetMapping("user_login")
	public String userLogin(HttpSession session) {
		log.info("Call user_login ");
		if(session.getAttribute("user")==null) {//세션정보가 없는 경우
			return "User/user_login";// 로그인 폼으로 화면 이동
		}else {//세션정보가 있는 경우
			return "User/user_list";//고객목록 화면으로 이동
		}
	}
	
	//회원가입 폼
	@GetMapping("user_insert")
	public void userInsert() {
		log.info("Call user_insert ");
	}
	
	@ResponseBody
	@PostMapping("user_idcheck")
	public String userIDCheck(@RequestParam("userid") String userid) {
		log.info("Call user_idcheck");
		int row = service.userIDCheck(userid);
		
		return String.valueOf(row);//문자열로 변환후 반환
		//return row;
	}

/*	
	//회원가입 처리(비번 SHA256 암호화 -> 컨트롤러에서 암호화)
	@PostMapping("user_insert")
	public String UserWritePro(UserDTO dto, RedirectAttributes rttr) {
		log.info("UserWritePro..............");
		
		String encryPassword = UserSha256.encrypt(dto.getPasswd());
		dto.setPasswd(encryPassword);
		
		int row = service.userInsert(dto);
		rttr.addFlashAttribute("row", row);
		return "redirect:/User/user_insert_pro";
	}
*/
	
	//회원가입 처리(비번 SHA256 암호화) -> 서비스에서 암호화 처리
	@PostMapping("user_insert")
	public String UserWritePro(UserDTO dto, RedirectAttributes rttr) {		
		int row = service.userInsert(dto);
		rttr.addFlashAttribute("row", row);
		return "redirect:/User/user_insert_pro";
	}	
	
	// 등록 완료 메시지 출력
	@GetMapping("user_insert_pro")
	public void UserWriteProMsg() {
		log.info("UserWriteProMsg..............");
	}
	

}
