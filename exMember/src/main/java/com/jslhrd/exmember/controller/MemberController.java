package com.jslhrd.exmember.controller;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jslhrd.exmember.domain.MemberDTO;
import com.jslhrd.exmember.mapper.MemberMapper;

@Controller
@RequestMapping("Member")
public class MemberController {
	private Logger log= LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberMapper mapper;
	
	@GetMapping("userinfo_list")
	public void memberList(Model model) {
		model.addAttribute("list", mapper.memberList());
		log.info("list  :  " + mapper.memberList());
	}
	
	@GetMapping("id_check")
	public void id_check() {
		
	}
	
	@GetMapping("post_check")
	public void post_check() {
		
	}
	
	@GetMapping("userinfo_insert")
	public void memberInsert() {
		
	}
	
	@PostMapping("userinfo_insert")
	public String memberInsertPro(MemberDTO dto) {
		if(dto.getGubun()==null)
			dto.setGubun("");
		if(dto.getZip()==null)
			dto.setZip("");
		if(dto.getAddr1()==null)
			dto.setAddr1("");
		if(dto.getAddr2()==null)
			dto.setAddr2("");
		if(dto.getFavorite()==null)
			dto.setFavorite("");
		if(dto.getJob()=="0")
			dto.setGubun("");
		if(dto.getIntro()==null)
			dto.setIntro("");
		mapper.memberInsert(dto);
		return "redirect:userinfo_list";
	}
	
	@GetMapping("userinfo_modify")
	public void memberModify(@RequestParam("userid") String userid, Model model) {
		model.addAttribute("member", mapper.memberSelect(userid));
	}
	
	@PostMapping("userinfo_modify")
	public String memberModifyPro(MemberDTO dto) {
		if(dto.getGubun()==null)
			dto.setGubun(mapper.memberSelect(dto.getUserid()).getGubun());
		if(dto.getZip()==null)
			dto.setZip(mapper.memberSelect(dto.getUserid()).getZip());
		if(dto.getAddr1()==null)
			dto.setAddr1(mapper.memberSelect(dto.getUserid()).getAddr1());
		if(dto.getAddr2()==null)
			dto.setAddr2(mapper.memberSelect(dto.getUserid()).getAddr2());
		if(dto.getFavorite()==null)
			dto.setFavorite(mapper.memberSelect(dto.getUserid()).getFavorite());
		if(dto.getJob()=="0")
			dto.setGubun(mapper.memberSelect(dto.getUserid()).getJob());
		if(dto.getIntro()==null)
			dto.setIntro(mapper.memberSelect(dto.getUserid()).getIntro());
		mapper.memberModify(dto);
		return "redirect:userinfo_list";
	}
}
