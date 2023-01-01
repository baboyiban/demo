package com.ezen.demo.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.ezen.demo.vo.Emp;

@Repository
public class JdbcTemDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// public
	public List<Emp> getListEmp() {
		String sql = "SELECT * FROM emp3 ORDER BY empno";
		return jdbcTemplate.query(sql, (rs, i) -> { // select
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt("EMPNO"));
			emp.setEname(rs.getString("ENAME"));
			emp.setDeptno(rs.getInt("deptno"));
			emp.setSal(rs.getFloat("sal"));
			emp.setHiredate(rs.getDate("hiredate"));
			return emp;
		});
	}

	public List<Emp> getListEmp(int deptno) {
		String sql = "SELECT * FROM emp3 WHERE deptno=? ORDER BY empno";
		return jdbcTemplate.query(sql, (rs, i) -> { // select
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt("EMPNO"));
			emp.setEname(rs.getString("ENAME"));
			emp.setDeptno(rs.getInt("deptno"));
			emp.setSal(rs.getFloat("sal"));
			emp.setHiredate(rs.getDate("hiredate"));
			return emp;
		}, deptno);
	}

	public int insertEmp(Emp emp) {
		String sql = "INSERT INTO emp3 (empno,ename,deptno,sal,hiredate) VALUES (emp_empno.nextval,?,?,?,SYSDATE)";
		// return jdbcTemplate.update(sql, emp.getEmpno(), emp.getEname(),
		// emp.getDeptno(), emp.getSal(), emp.getHiredate()) > 0 ? true : false; //
		// insert,update,delete
		GeneratedKeyHolder kh = new GeneratedKeyHolder();
		int result = jdbcTemplate.update((conn) -> {
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql, new String[] { "empno" });
			pstmt.setString(1, emp.getEname());
			pstmt.setInt(2, emp.getDeptno());
			pstmt.setFloat(3, emp.getSal());
			return pstmt;
		}, kh);
		System.out.println("kh:" + kh.getKey());
		return result;
	}

	public Emp getEmp(int empno) {
		String sql = "SELECT * FROM emp3 WHERE empno=?";
		return jdbcTemplate.queryForObject(sql, (rs, i) -> { // select
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt("EMPNO"));
			emp.setEname(rs.getString("ENAME"));
			emp.setDeptno(rs.getInt("deptno"));
			emp.setSal(rs.getFloat("sal"));
			emp.setHiredate(rs.getDate("hiredate"));
			return emp;
		}, empno);
	}

	public int getEmpno() {
		return jdbcTemplate.query("SELECT * FROM emp3 ORDER BY empno DESC", (rs, i) -> {
			return rs.getInt("empno");
		}).get(0);
	}

	public int delEmp(int empno) {
		String sql = "DELETE FROM emp3 WHERE empno=?";
		return jdbcTemplate.update(sql, empno);
	}

	public int editEmp(Emp emp) {
		String sql = "UPDATE emp3 SET ename=?,deptno=?,sal=? WHERE empno=?";
		return jdbcTemplate.update(sql, emp.getEname(), emp.getDeptno(), emp.getSal(), emp.getEmpno());
	}

	public boolean isDeptno(int deptno) {
		String sql = "SELECT * FROM dept WHERE deptno=?";
		try {
			jdbcTemplate.queryForObject(sql, (rs, i) -> {
				return rs.getString("deptno");
			}, deptno);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	// private
}
