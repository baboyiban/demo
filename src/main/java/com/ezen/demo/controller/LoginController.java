package com.ezen.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ezen.demo.service.LoginService;
import com.ezen.demo.vo.User;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
@SessionAttributes("id")
public class LoginController {
	@Autowired
	private LoginService svc;
	@Autowired
	private HttpSession session;
	// login
	@GetMapping({"","/"})
	public String login(Model m) {
		return "login/login";
	}
	@PostMapping({"","/"})
	@ResponseBody
	public Map<String,String> login(User user, Model m) {
		Map<String,String> map = new HashMap<>();
		map.put("result", svc.login(user,m));
		return map;
	}
	// logout
	@PostMapping("/logout")
	@ResponseBody
	public Map<String,String> logout(SessionStatus status) {
		System.out.println("logout");
		Map<String,String> map = new HashMap<>();
		map.put("result", svc.logout(status));
		return map;
	}
}
