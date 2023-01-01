package com.ezen.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ezen.demo.vo.Emp;

@Repository
public class EmpDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// public
	public List<Emp> getListEmp() {
		getConn();
		try {
			String sql = "SELECT * FROM emp3 ORDER BY empno";
			this.pstmt = conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();
			List<Emp> listEmp = new ArrayList<>();
			while (this.rs.next()) {
				int empno = this.rs.getInt("empno");
				String ename = this.rs.getString("ename");
				int deptno = this.rs.getInt("deptno");
				float sal = this.rs.getFloat("sal");
				java.sql.Date hiredate = this.rs.getDate("hiredate");
				Emp emp = new Emp(empno, ename, deptno, sal, hiredate);
				listEmp.add(emp);
			}
			return listEmp;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}

	public int getEmpno() {
		getConn();
		try {
			String sql = "SELECT empno FROM emp3 ORDER BY empno DESC";
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();
			if (this.rs.next()) {
				return this.rs.getInt("empno") + 1;
			} else {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return 0;
	}

	public boolean insertEmp(Emp emp) {
		getConn();
		try {
			System.out.println("emp:" + emp);
			String sql = "INSERT INTO emp3 (empno,ename,deptno,sal,hiredate) VALUES (emp_empno.nextval,?,?,?,SYSDATE)";
			this.pstmt = this.conn.prepareStatement(sql);
			// 마지막 empno 번호 + 1
			this.pstmt.setString(1, emp.getEname());
			this.pstmt.setInt(2, emp.getDeptno());
			this.pstmt.setFloat(3, emp.getSal());
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return false;
	}

	public Emp getEmp(int empno) {
		getConn();
		try {
			String sql = "SELECT * FROM emp3 WHERE empno=?";
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, empno);
			this.rs = this.pstmt.executeQuery();
			if (this.rs.next()) {
				Emp emp = new Emp();
				emp.setEmpno(this.rs.getInt("empno"));
				emp.setEname(this.rs.getString("ename"));
				emp.setDeptno(this.rs.getInt("deptno"));
				emp.setSal(this.rs.getFloat("sal"));
				emp.setHiredate(this.rs.getDate("hiredate"));
				return emp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}

	public boolean delEmp(int empno) {
		getConn();
		try {
			String sql = "DELETE FROM emp3 WHERE empno=?";
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, empno);
			return this.pstmt.executeUpdate() > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return false;
	}

	public boolean editEmp(Emp emp) {
		getConn();
		try {
			String sql = "UPDATE emp3 SET ename=?, deptno=?, sal=? WHERE empno=?";
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setString(1,emp.getEname());
			this.pstmt.setInt(2, emp.getDeptno());
			this.pstmt.setFloat(3, emp.getSal());
			this.pstmt.setInt(4, emp.getEmpno());
			return this.pstmt.executeUpdate()>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return false;
	}
	
	public boolean isDeptno(int deptno) {
		getConn();
		try {
			String sql = "SELECT * FROM dept WHERE deptno=?";
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, deptno);
			this.rs = this.pstmt.executeQuery();
			return this.rs.next();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return false;
	}

	// private
	private void getConn() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closeAll() {
		try {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int empnoNextval() {
		getConn();
		try {
			String sql = "SELECT emp_empno.NEXTVAL as empno FROM dual";
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();
			if (this.rs.next()) {
				return this.rs.getInt("empno");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
