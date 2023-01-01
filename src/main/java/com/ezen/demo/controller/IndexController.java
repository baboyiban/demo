package com.ezen.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.service.GuguService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
	@Autowired
	private GuguService svc;
	
	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/gugu")
	public String gugu(HttpServletRequest request) {
		String sDan = request.getParameter("dan");

		int dan = 2;
		if (sDan != null)
			dan = Integer.valueOf(sDan);

		request.setAttribute("dan", dan);

		return "gugu";
	}

	@GetMapping("/gugu2")
	public String gugu2(@RequestParam(value = "dan", defaultValue = "2") int dan, Model m) {
		m.addAttribute("dan", dan);

		return "gugu";
	}

	@GetMapping("/gugu3")
	public String gugu3(@RequestParam(value = "dan", defaultValue = "2") int dan, Model m) {
		List<String> list = svc.createGugu(dan);
		m.addAttribute("list", list);
		return "gugu";
	}

	@GetMapping({"/gugu4","/gugu4/{dan}"})
	public String gugu4(@PathVariable(name="dan") Optional<Integer> dan, Model m) {
		List<String> list = new ArrayList<>();
		if (dan.isPresent()) {
			list = svc.createGugu(dan.get());
		}
		m.addAttribute("list", list);
		return "gugu";
	}
	
	@GetMapping("/add/{a}/{b}")
	@ResponseBody
	public String add(@PathVariable("a") int a, @PathVariable("b") int b) {
		String msg = String.format("%d + %d = %d", a, b, a+b);
		return msg;
	}
	
	@GetMapping("/mul/{a}/{b}")
	@ResponseBody
	public String mul(@PathVariable Map<String, String> map) {
		String msg = String.format("%s + %s = %d", map.get("a"), map.get("b"), Integer.valueOf(map.get("a"))*Integer.valueOf(map.get("b")));
		return msg;
	}
}
