package com.jslhrd.exboard.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jslhrd.exboard.domain.BoardDTO;
import com.jslhrd.exboard.domain.PageDTO;
import com.jslhrd.exboard.service.BoardService;
import com.jslhrd.exboard.util.PageIndex;

@Controller
@RequestMapping("Board")
public class BoardController {
	private Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
/*
	//PageIndex(x)
	@GetMapping("board_list")
	public void boardList(Model model) {
		int cnt = service.boardCount();
		model.addAttribute("count", cnt);
		model.addAttribute("list", service.boardList());
	}
*/	
	//PageIndex(o), Search(x)
	@GetMapping("board_list")
	public void boardList(@RequestParam("page") int page, PageDTO dto, Model model) {
		String url = "board_list";
		
		int nowpage = 1;
		int maxlist = 10;
		int totpage = 1;
		
		//총 게시글 수
		int totcount = service.boardCount();
		
		//총 페이지 수 계산
		if(totcount % 10 == 0)
			totpage = totcount / maxlist;
		else
			totpage = totcount / maxlist + 1;
		
		if(totpage == 0) 
			totpage = 1;
		
		if(page != 0) 
			nowpage = page;
		
		//현재 페이지 시작번호 구하기
		int startpage = (nowpage-1) * maxlist + 1;
		int endpage = nowpage * maxlist;
		
		//dto는 자동전송
		dto.setStartpage(startpage);
		dto.setEndpage(endpage);
		
		//게시글번호
		int listcount = totcount - ((nowpage - 1) * maxlist); 
		
		model.addAttribute("page", nowpage);
		model.addAttribute("totpage", totpage);
		model.addAttribute("totcount", totcount);
		model.addAttribute("listcount", listcount);
		model.addAttribute("list", service.boardList(dto));
		model.addAttribute("pageList", PageIndex.pageList(nowpage, totpage, url, ""));
	}
	
	@GetMapping("board_write")
	public void boardWrite(@RequestParam("page") int page, Model model) {
		model.addAttribute("page", page);
	}
	
	@PostMapping("board_write")
	public String boardWritePro(@RequestParam("page") int page, BoardDTO dto) {
		service.boardWrite(dto);
		return "redirect:board_list?page="+page;
	}
	
/*	@PostMapping("board_write")
	public String boardWritePro(BoardDTO board, Model model) {
		int row = mapper.boardWrite(board);
		model.addAttribute("row", row);
		log.info("Call  :  board_write 등록처리" );
		
		return "Board/board_write_pro";//jsp 파일 경로(경구문구 출력)
	}
*/	
	@GetMapping("board_hits")
	public String guestHits(@RequestParam("idx") int idx, @RequestParam("page") int page, HttpServletRequest request, HttpServletResponse response) {
		service.boardHits(idx, request, response);
		return "redirect:board_view?idx="+idx+"&page="+page;
	}
	
	@GetMapping("board_view")
	public void boardView(@RequestParam("idx") int idx, @RequestParam("page") int page, Model model) {
		BoardDTO board = service.boardSearch(idx);
		board.setContents(board.getContents().replace("\n", "<br>"));
		model.addAttribute("board", board);
		model.addAttribute("page", page);
	}
	
	@GetMapping("board_modify")
	public void boardModify(@RequestParam("idx") int idx, @RequestParam("page") int page, Model model) {
		model.addAttribute("board", service.boardSearch(idx));
		model.addAttribute("page", page);
	}
	
	@PostMapping("board_modify")
	public String boardModifyPro(@RequestParam("page") int page, BoardDTO dto) {
		service.boardModify(dto);
		return "redirect:board_list?page="+page;
	}
	
	@GetMapping("board_delete")
	public void boardDelete(@RequestParam("idx") int idx, @RequestParam("page") int page, Model model) {
		model.addAttribute("idx", idx);
		model.addAttribute("page", page);
	}
	
/*	@GetMapping("board_delete")
	public void boardDelete(@ModelAttribute("idx") int idx) {
		log.info("Call  :  board_delete (삭제 폼)" );
	}
*/	
	
	@PostMapping("board_delete")
	public String boardDeletePro(@RequestParam("idx") int idx, @RequestParam("page") int page, @RequestParam("pass") String pass, RedirectAttributes rttr) {
		rttr.addFlashAttribute("row", service.boardDelete(idx, pass));
		return "redirect:board_delete_pro?page="+page;
	}
	
	//삭제처리 알림
	@GetMapping("board_delete_pro")
	public void boardDeletePass(@RequestParam("page") int page, Model model) {
		model.addAttribute("page", page);
	}
}
