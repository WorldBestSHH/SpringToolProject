package com.jslhrd.expds.controller;

import java.io.File;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jslhrd.expds.domain.PdsDTO;
import com.jslhrd.expds.service.PdsService;

@Controller
@RequestMapping("Pds")
public class PdsController {
	private Logger log=LoggerFactory.getLogger(PdsController.class);
	
	@Autowired
	private PdsService service;
	
	@GetMapping("pds_list")
	public void pdsList(Model model) {
		int cnt = service.pdsCount();
		model.addAttribute("count", cnt);
		model.addAttribute("list", service.pdsList());
	}
	
	@GetMapping("pds_write")
	public void pdsWrite() {

	}
	
	@PostMapping("pds_write")
	public String pdsWritePro(MultipartHttpServletRequest request) {
		PdsDTO dto = new PdsDTO();
		dto.setName(request.getParameter("name"));
		dto.setPass(request.getParameter("pass"));
		dto.setEmail(request.getParameter("email"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContents(request.getParameter("contents"));
		
		//파일저장경로
		String path=request.getServletContext().getRealPath("/upload/");
		MultipartFile mf = request.getFile("filename");
		String fileName = mf.getOriginalFilename();
		File file = new File(path+fileName);
		try {
			mf.transferTo(file);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		dto.setFilename(fileName);
		service.pdsWrite(dto);
		
		return "redirect:pds_list";
	}
	
	@GetMapping("pds_hits")
	public String pdsHits(@RequestParam("idx") int idx, HttpServletRequest request, HttpServletResponse response) {
		service.pdsHits(idx, request, response);
		return "redirect:pds_view?idx="+idx;
	}
	
	@GetMapping("pds_view")
	public void pdsView(@RequestParam("idx") int idx, Model model) {
		PdsDTO pds = service.pdsSearch(idx);
		pds.setContents(pds.getContents().replace("\n", "<br>"));
		model.addAttribute("pds", pds);
	}
	
	@GetMapping("pds_modify")
	public void pdsModify(@RequestParam("idx") int idx, Model model) {
		model.addAttribute("pds", service.pdsSearch(idx));
	}
	
	@PostMapping("pds_modify")
	public String pdsModifyPro(@RequestParam("idx") int idx, MultipartHttpServletRequest request) {
		PdsDTO dto = new PdsDTO();
		
		String filename = request.getParameter("filename");
		String filename1 = request.getParameter("filename1");
		
		dto.setIdx(idx);
		dto.setPass(request.getParameter("pass"));
		dto.setEmail(request.getParameter("email"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContents(request.getParameter("contents"));
		dto.setFilename(filename);
		
		String path=request.getServletContext().getRealPath("/upload/");
		
		if(filename == null) {
			dto.setFilename(filename1);
		}else if(filename != null) {
			File file = new File(path+filename1);
			if(file.exists())
				file.delete();
		}
		
		service.pdsModify(dto);
		
		return "redirect:pds_list";
	}
	
	@GetMapping("pds_delete")
	public void pdsDelete(@RequestParam("idx") int idx, Model model) {
		model.addAttribute("idx", idx);
	}
	
	@PostMapping("pds_delete")
	public String pdsDeletePro(@RequestParam("idx") int idx, @RequestParam("pass") String pass, RedirectAttributes rttr) {
		rttr.addFlashAttribute("row", service.pdsDelete(idx, pass));
		return "redirect:pds_delete_pro";
	}
	
	//삭제처리 알림
	@GetMapping("pds_delete_pro")
	public void pdsDeletePass() {

	}	
}
