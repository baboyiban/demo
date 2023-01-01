package com.ezen.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ezen.demo.dao.LoginDAO;
import com.ezen.demo.vo.User;

import jakarta.servlet.http.HttpSession;

@Service
@RequestMapping("/user")
public class LoginService {
	@Autowired
	private LoginDAO dao;
	// login
	public String login(User user, Model m) {
		if (dao.login(user)) {
			m.addAttribute("id", user.getId());
			return "로그인 성공";
		}
		return "로그인 실패";
	}
	// logout
	public String logout(SessionStatus status) {
		status.setComplete();
		return status.isComplete()?"로그아웃 성공":"로그아웃 실패";
	}
}
