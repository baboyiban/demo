package com.ezen.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ezen.demo.vo.Emp;

@Mapper
public interface EmpMapper {
	// list
	public List<Emp> getListEmp();
	public List<Emp> getListEmpByDeptno(int deptno);
	public List<Map<String,Object>> getListEmpWithDname(Map<String,Object> map);
	// emp
	public List<Emp> getListEmpByEmpno(int empno);
	public int addEmp(Emp emp);
	// empno
	public int getEmpno();
	// deptno
	public Integer isDeptno(int deptno); 
	// delete
	public int deleteByEmpno(int empno);
	// edit
	public int editEmp(Emp emp);
}
