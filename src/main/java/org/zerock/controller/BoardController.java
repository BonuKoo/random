package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.pageDTO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@Slf4j
@RequestMapping("/board/*")
@RequiredArgsConstructor // public final 변수명 기입된 것은 자동 주입
public class BoardController {
	private final BoardService service;

	@GetMapping("register")
	public void register() {
	}

//	@GetMapping("list")
//	public void list(Model model) {
//		log.info("list");
//		model.addAttribute("list",service.getList());
//	}

	@GetMapping("list") // 2, 10 총 16개. 2페이지에 10개가 보여아 한다면
	// 2page에 11~20개가 출력
	// pageDTO
	public void list(Criteria cri, Model model) {
		log.info("list" + cri);
		model.addAttribute("list", service.getList(cri)); // 역순으로
	//	model.addAttribute("pageMaker", new pageDTO(cri, 157));
	int total = service.getTotal(cri);
	log.info("total:" + total);
	model.addAttribute("pageMaker",new pageDTO(cri,total));
	}

	@PostMapping("register")
	public String register(BoardVO vo, RedirectAttributes rttr) {
		log.info("register : {}", vo);
		service.register(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		return "redirect:/board/list"; // 화면 이동
	}

	@GetMapping({ "get", "/modify" }) // 상세페이지
	public void get(@RequestParam("bno") Long bno, Criteria cri , Model model) {
		log.info("/get or /modify : {}", bno);
		model.addAttribute("board", service.get(bno));
		
		service.get(bno);
	}

	@PostMapping("remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("/remove : {} ", bno);
		if (service.remove(bno)) {
			rttr.addAttribute("result", "succecs");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list";
	}

	@PostMapping("modify")
	public String modify(BoardVO vo, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify : {} ", vo);

		if (service.modify(vo)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list";
	}
	
	
}
