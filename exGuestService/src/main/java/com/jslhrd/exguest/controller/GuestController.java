package com.jslhrd.exguest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jslhrd.exguest.domain.GuestDTO;
import com.jslhrd.exguest.service.GuestService;

@Controller
@RequestMapping("Guest") //get, post
public class GuestController {
	private static final Logger log = LoggerFactory.getLogger(GuestController.class);
	
	@Autowired
	private GuestService service;
	
	@GetMapping("/guest_list") //get
	public void guestList(Model model) {
		log.info("Call : guest_list");
		int cnt = service.guestCount();
		model.addAttribute("count", cnt);
		model.addAttribute("list", service.guestList());
/*		List<GuestDTO> list = mapper.guestList();
		model.addAttribute("list", list);
*/		
	}
	
	@GetMapping("guest_write")
	public void guestWrite() {
		log.info("Call : guest_write");
	}
	
	@PostMapping("guest_write")
	public String guestWritePro(GuestDTO dto) {
		log.info("Call : guest_write_pro");
		log.info("dto : " + dto);
		service.guestWrite(dto);
		return "redirect:guest_list";
	}
	
	@GetMapping("guest_hits")
	public String guestHits(@RequestParam("idx") int idx, HttpServletRequest request, HttpServletResponse response) {
		log.info("Call  :  guest_hits");
		service.guestHits(idx, request, response);
		
		return "redirect:guest_view?idx="+idx;
		
	}
	@GetMapping("guest_view")
	public void guestView(@RequestParam("idx") int idx, Model model) {
		log.info("Call  :  guest_view ");
		model.addAttribute("guest", service.guestSearch(idx));
		
	}
	
	
	@GetMapping("guest_modify")
	public void guestModify(@RequestParam("idx") int idx, Model model) {
		log.info("Call : guest_modify");
		model.addAttribute("guest", service.guestSearch(idx));
	}
	
	@PostMapping("guest_modify")
	public String guestModifyPro(GuestDTO dto) {
		log.info("Call : guest_modify_pro");
		service.guestModify(dto);
		return "redirect:guest_list";
	}
	
	@GetMapping("guest_delete")
	public String guestDelete(@RequestParam("idx") int idx) {
		log.info("Call : guest_delete");
		service.guestDelete(idx);
		return "redirect:guest_list";
	}
}
