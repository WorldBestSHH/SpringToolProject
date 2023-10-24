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
import com.jslhrd.exboard.service.BoardService;

@Controller
@RequestMapping("Board")
public class BoardController {
	private Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@GetMapping("board_list")
	public void boardList(Model model) {
		int cnt = service.boardCount();
		model.addAttribute("count", cnt);
		model.addAttribute("list", service.boardList());
	}
	
	@GetMapping("board_write")
	public void boardWrite() {
		
	}
	
	@PostMapping("board_write")
	public String boardWritePro(BoardDTO dto) {
		service.boardWrite(dto);
		return "redirect:board_list";
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
	public String guestHits(@RequestParam("idx") int idx, HttpServletRequest request, HttpServletResponse response) {
		service.boardHits(idx, request, response);
		return "redirect:board_view?idx="+idx;
	}
	
	@GetMapping("board_view")
	public void boardView(@RequestParam("idx") int idx, Model model) {
		BoardDTO board = service.boardSearch(idx);
		board.setContents(board.getContents().replace("\n", "<br>"));
		model.addAttribute("board", board);
	}
	
	@GetMapping("board_modify")
	public void boardModify(@RequestParam("idx") int idx, Model model) {
		model.addAttribute("board", service.boardSearch(idx));
	}
	
	@PostMapping("board_modify")
	public String boardModifyPro(BoardDTO dto) {
		service.boardModify(dto);
		return "redirect:board_list";
	}
	
	@GetMapping("board_delete")
	public void boardDelete(@RequestParam("idx") int idx, Model model) {
		model.addAttribute("idx", idx);
	}
	
/*	@GetMapping("board_delete")
	public void boardDelete(@ModelAttribute("idx") int idx) {
		log.info("Call  :  board_delete (삭제 폼)" );
	}
*/	
	
	@PostMapping("board_delete")
	public String boardDeletePro(@RequestParam("idx") int idx, @RequestParam("pass") String pass, RedirectAttributes rttr) {
		rttr.addFlashAttribute("row", service.boardDelete(idx, pass));
		return "redirect:board_delete_pro";
	}
	
	//삭제처리 알림
	@GetMapping("board_delete_pro")
	public void boardDeletePass() {
		log.info("boardDeletePro() . OK.......");
	}
}
