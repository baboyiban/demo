package com.ezen.demo.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(exclude= {"ename","deptno","sal","hiredate"})
@AllArgsConstructor
@NoArgsConstructor
public class Emp implements Serializable, Comparable<Emp> {
	private int empno;
	private String ename;
	private int deptno;
	private float sal;
	private java.sql.Date hiredate;
	@Override
	public int compareTo(Emp o) {
		if (this.getEmpno() > o.getEmpno()) return 1;
		if (this.getEmpno() < o.getEmpno()) return -1;
		return 0;
	}
}
