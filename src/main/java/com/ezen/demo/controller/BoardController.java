package com.ezen.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.demo.service.BoardService;
import com.ezen.demo.vo.Board;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService svc;

	@ModelAttribute("id")
	public String getId() {
		return "smith";
	}

	@GetMapping({ "", "/", "/list" })
	public String goBoardList(Model m) {
		List<Map<String,Object>> list = svc.getList();
		System.out.println("list:" + list);
		m.addAttribute("list", list);
		return "board/boardList";
	}

	// add
	@GetMapping({ "/add" })
	public String goBoardAdd(Model m, Integer id) {
		return "board/boardAdd";
	}

	@PostMapping({ "/add" })
	@ResponseBody
	public Map<String, Object> addBoard(HttpServletRequest request, Board board, MultipartFile[] files) {
		// test
		System.out.println("b:" + board);
		System.out.println("files:" + files);
		// main
		Map<String, Object> map = new HashMap<>();
		map.put("result", svc.saveBoardFiles(request, board, files));
		return map;
	}

	// detail
	@GetMapping({ "/detail" })
	public String goBoardDetail(Model m, int num) {
		if (num != 0) {
			m.addAttribute("board", svc.getBoard(num));
			m.addAttribute("listAttach", svc.getListAttach(num));
		}
		return "board/boardDetail";
	}
	
	// download
	@GetMapping({"/download"})
	public ResponseEntity<Resource> download(HttpServletRequest request, int num) {
		return svc.getResource(request, num);
	}
}
