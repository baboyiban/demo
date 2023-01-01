package com.ezen.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.demo.dao.EmpDAO;
import com.ezen.demo.dao.JdbcTemDAO;
import com.ezen.demo.vo.Emp;

@Service
public class EmpService {
	@Autowired
	private EmpDAO dao;
	@Autowired
	private JdbcTemDAO jtd;

	// list
	public List<Emp> getListEmp() {
		return jtd.getListEmp();
	}

	public List<Emp> getListEmp(int deptno) {
		return jtd.getListEmp(deptno);
	}

	// empno
	public int getEmpno() {
		return jtd.getEmpno();
	}

	// add
	public String addEmp(Emp emp) {
		return jtd.insertEmp(emp) > 0 ? "추가 성공" : "추가 실패";
	}

	// getEmp
	public Emp getEmp(int empno) {
		return jtd.getEmp(empno);
	}

	// delEmp
	public String delEmp(int empno) {
		return jtd.delEmp(empno) > 0 ? "삭제 성공" : "삭제 실패";
	}

	// editEmp
	public String editEmp(Emp emp) {
		if (jtd.isDeptno(emp.getDeptno()))
			return jtd.editEmp(emp) > 0 ? "수정 성공" : "수정 실패";
		else
			return "deptno 맞지 않습니다";
	}

	// deptno
}
