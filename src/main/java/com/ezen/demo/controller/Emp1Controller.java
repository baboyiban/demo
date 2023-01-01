package com.ezen.demo.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

import com.ezen.demo.service.Emp1Service;
import com.ezen.demo.vo.Emp;

import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Controller
@RequestMapping("/emp1")
@Slf4j
public class Emp1Controller {
	@Autowired
	private Emp1Service svc;
	
	@ModelAttribute("path")
	public String getPath() {
		return "/emp1";
	}

	// main
	@GetMapping({ "", "/" })
	public String showForm() {
		return "emp/empList";
	}
	//

	// add
	@GetMapping("/add")
	public String showAddForm(Model m) {
		// 사번, 이름, 부서, 급여, 입사일
		log.info("폼 요청함");
		// log.debug(null);
		// log.warn();
		// log.error(null);
		m.addAttribute("empno", svc.currEmpno());
		return "emp/empAdd";
	}

	@PostMapping("/add")
	@ResponseBody
	public Map<String, String> addEmp(Emp emp) {
		Map<String, String> map = new HashMap<>();
		map.put("result", svc.addEmp(emp));
		svc.nextEmpno();
		return map;
	}
	//

	// list
	@GetMapping("/list")
	public String showListForm(Model m) {
		List<Emp> listEmp = svc.getListEmp();
		m.addAttribute("listEmp", listEmp);
		return "emp/empList";
	}
	//

	// edit
	@GetMapping("/edit")
	public String showEditForm(String empno, Model m) {
		m.addAttribute("emp", svc.getEmp(Integer.valueOf(empno)));
		return "emp/empEdit";
	}

	@PostMapping("/edit")
	@ResponseBody
	public Map<String, String> edit(Emp emp) {
		Map<String, String> map = new HashMap<>();
		map.put("result", svc.editEmp(emp));
		return map;
	}
	//

	// detail
	@GetMapping("/detail")
	public String showDetailForm(String empno, Model m) {
		m.addAttribute("emp", svc.getEmp(Integer.valueOf(empno)));
		return "emp/empDetail";
	}
	//

	// delete
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, String> delete(Emp emp) {
		Map<String, String> map = new HashMap<>();
		map.put("result", svc.delEmp(emp));
		return map;
	}
	//

	// deptno
	@PostMapping("/deptno")
	@ResponseBody
	public Map<String, String> getDeptno() {
		Map<String, String> map = new HashMap<>();
		/*
		boolean result = false;
		int[] iarr = svc.getDeptno();
		for (int i = 0; i < iarr.length; i++) {
			if (iarr[i] == deptno) {
				result = true;
				break;
			}
		}
		map.put("result", result);
		*/
		map.put("result","true");
		return map;
	}
	//
}
