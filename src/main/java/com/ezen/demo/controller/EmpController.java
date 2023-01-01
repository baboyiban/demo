package com.ezen.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ezen.demo.dao.JdbcTemDAO;
import com.ezen.demo.service.EmpService;
import com.ezen.demo.vo.Emp;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/emp")
@Slf4j
@SessionAttributes("id")
public class EmpController {
	@Autowired
	private EmpService svc;
	@Autowired
	private JdbcTemDAO jtd;
	
	@ModelAttribute("path")
	public String getPath() {
		return "/emp";
	}

	// main
	@GetMapping({ "", "/", "/list" })
	public String main(Model m, String deptno) {
		System.out.println("deptno:"+deptno);
		if (deptno == null || deptno.equals(""))
			m.addAttribute("listEmp", svc.getListEmp());
		else
			m.addAttribute("listEmp", svc.getListEmp(Integer.valueOf(deptno)));
		return "emp/empList";
	}

	// add
	@GetMapping("/add")
	public String add(Model m) {
		int empno = svc.getEmpno();
		m.addAttribute("empno", empno);
		return "emp/empAdd";
	}

	@PostMapping("/add")
	@ResponseBody
	public Map<String, String> addEmp(Emp emp, @SessionAttribute(name = "id", required = false) String id) {
		Map<String, String> map = new HashMap<>();
		if (id != null) {
			String result = svc.addEmp(emp);
			map.put("result", result);
		} else {
			map.put("result", "로그인을 하지 않았습니다");
		}
		return map;
	}

	// deptno
	@PostMapping("/deptno")
	@ResponseBody
	public Map<String, Boolean> getDeptno(int deptno) {
		Map<String, Boolean> map = new HashMap<>();
		boolean result = false;
		for (int i = 0; i < 3; i++) {
			if (deptno == ((i + 1) * 10)) {
				result = true;
				break;
			}
		}
		map.put("result", result);
		return map;
	}

	// detail
	@GetMapping("/detail")
	public String detail(Model m, String empno) {
		m.addAttribute("emp", svc.getEmp(Integer.valueOf(empno)));
		return "emp/empDetail";
	}

	// delete
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, String> delete(int empno, @SessionAttribute(name = "id", required = false) String id) {
		Map<String, String> map = new HashMap<>();
		if (id != null) {
			map.put("result", svc.delEmp(empno));
		} else {
			map.put("result", "로그인을 하지 않았습니다");
		}
		return map;
	}

	// edit
	@GetMapping("/edit")
	public String edit(Model m, String empno) {
		m.addAttribute("emp", svc.getEmp(Integer.valueOf(empno)));
		return "/emp/empEdit";
	}

	@PostMapping("/edit")
	@ResponseBody
	public Map<String, String> edit(Emp emp, @SessionAttribute(name = "id", required = false) String id) {
		Map<String, String> map = new HashMap<>();
		if (id != null) {
			String result = svc.editEmp(emp);
			map.put("result", result);
		} else {
			map.put("result", "로그인을 하지 않았습니다");
		}
		return map;
	}

	@GetMapping("/test")
	@ResponseBody
	public Object test() {
		return jtd.getListEmp();
	}
}
