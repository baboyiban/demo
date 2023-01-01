package com.ezen.demo.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.mapper.EmpMapper;
import com.ezen.demo.vo.Emp;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/mybatis/emp")
@Slf4j
public class MybatisEmpController {
	@Autowired
	private EmpMapper dao;

	@ModelAttribute("path")
	public String getPath() {
		return "/mybatis/emp";
	}

	// list
	@GetMapping({ "", "/", "/list" })
	public String list(Model m,@RequestParam Map<String,Object> map) {
		List<Map<String, Object>> listEmp = dao.getListEmpWithDname(map);
		changeLowerKey(listEmp);
		m.addAttribute("listEmp",listEmp);
		return "emp/empList2";
	}

	// detail
	@GetMapping("/detail")
	public String detail(Model m, String empno) {
		if (empno == null || empno.equals("")) {
		} else {
			m.addAttribute("emp", dao.getListEmpByEmpno(Integer.valueOf(empno)).get(0));
		}
		return "emp/empDetail";
	}

	// add
	@GetMapping("/add")
	public String add(Model m) {
		m.addAttribute("empno", dao.getEmpno());
		return "emp/empAdd";
	}

	@PostMapping("/add")
	@ResponseBody
	public Map<String, String> add(Emp emp) {
		Map<String, String> map = new HashMap<>();
		if (dao.isDeptno(emp.getDeptno()) != null) {
			map.put("result", dao.addEmp(emp) > 0 ? "추가 성공" : "추가 실패");
		} else {
			map.put("result", "deptno가 맞지 않습니다");
		}
		return map;
	}

	// delete
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, String> delete(int empno) {
		Map<String, String> map = new HashMap<>();
		map.put("result", dao.deleteByEmpno(empno) > 0 ? "삭제 성공" : "삭제 실패");
		return map;
	}

	// deptno
	@PostMapping("/deptno")
	@ResponseBody
	public Map<String, String> isDeptno(int deptno) {
		Map<String, String> map = new HashMap<>();
		map.put("result", dao.isDeptno(deptno) != null ? "true" : "false");
		return map;
	}

	// edit
	@GetMapping("/edit")
	public String edit(Model m, int empno) {
		m.addAttribute("emp", dao.getListEmpByEmpno(empno).get(0));
		return "emp/empEdit";
	}

	@PostMapping("/edit")
	@ResponseBody
	public Map<String, String> edit(Emp emp) {
		Map<String, String> map = new HashMap<>();
		if (dao.isDeptno(emp.getDeptno()) != null) {
			map.put("result", dao.editEmp(emp) > 0 ? "수정 성공" : "수정 실패");
		} else {
			map.put("result", "deptno가 맞지 않습니다");
		}
		return map;
	}

	private void changeLowerKey(List<Map<String, Object>> list) {
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = new HashMap<>();
			Iterator<String> iterator = list.get(i).keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				String lowerKey = key.toLowerCase();
				map.put(lowerKey, list.get(i).get(key));
			}
			list.set(i, map);
		}
	}
}
