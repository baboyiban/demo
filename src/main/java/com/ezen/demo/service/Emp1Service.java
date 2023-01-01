package com.ezen.demo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ezen.demo.vo.Emp;

@Service
public class Emp1Service {
	private String empserPath = "D:/학생방/최다루한/documents/emp.ser";
	private String empnoseqPath = "D:/학생방/최다루한/documents/empno.seq";
	private String deptnoPath = "D:/학생방/최다루한/documents/deptno.txt";

	// public
	public List<Emp> getListEmp() {
		return road();
	}

	public String addEmp(Emp emp) {
		try {
			List<Emp> listEmp = road();
			listEmp.add(emp);
			save(listEmp);
			return "사원정보 추가 성공";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "사원정보 추가 실패";
	}

	public Emp getEmp(int empno) {
		List<Emp> listEmp = road();
		for (int i = 0; i < listEmp.size(); i++) {
			if (listEmp.get(i).getEmpno() == empno) {
				return listEmp.get(i);
			}
		}
		return null;
	}

	public String editEmp(Emp emp) {
		List<Emp> listEmp = road();
		if (listEmp.contains(getEmp(emp.getEmpno()))) {
			listEmp.set(listEmp.indexOf(getEmp(emp.getEmpno())), emp);
			save(listEmp);
			return "사원정보 수정 성공";
		}
		return "사원정보 수정 실패";
	}

	public String delEmp(Emp emp) {
		List<Emp> listEmp = road();
		if (listEmp.contains(emp)) {
			listEmp.remove(listEmp.indexOf(emp));
			save(listEmp);
			return "사원정보 삭제 성공";
		}
		return "사원정보 삭제 실패";
	}

	public int nextEmpno() {
		try {
			File f = new File(empnoseqPath);
			if (f.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(f));
				Integer empnoseq = Integer.valueOf(br.readLine());
				empnoseq += 1;
				br.close();
				PrintWriter pw = new PrintWriter(new FileWriter(f));

				pw.print(String.valueOf(empnoseq));
				pw.flush();
				pw.close();
				return empnoseq;
			} else {
				PrintWriter pw = new PrintWriter(new FileWriter(f));
				int empnoseq = 1;
				System.out.println("empnoseq:" + empnoseq);
				pw.print(String.valueOf(empnoseq));
				pw.flush();

				pw.close();
				return empnoseq;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int currEmpno() {
		try {
			File f = new File(empnoseqPath);
			if (f.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(f));
				int empnoseq = Integer.valueOf(br.readLine());
				br.close();
				return empnoseq;
			} else {
				PrintWriter pw = new PrintWriter(new FileWriter(f));
				int empnoseq = 1;
				pw.print(String.valueOf(empnoseq));
				pw.flush();
				pw.close();
				BufferedReader br = new BufferedReader(new FileReader(f));
				empnoseq = Integer.valueOf(br.readLine());
				br.close();
				return empnoseq;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int[] getDeptno() {
		try {
			File f = new File(deptnoPath);
			if (f.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(f));
				String[] sDeptno = br.readLine().split(",");
				int[] deptno = new int[sDeptno.length];
				for (int i = 0; i < sDeptno.length; i++) {
					deptno[i] = Integer.valueOf(sDeptno[i]);
				}
				return deptno;
			} else {
				return new int[] {};
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//

	// private
	private List<Emp> road() {
		try {
			File file = new File(empserPath);
			if (file.exists()) {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				return (List<Emp>) ois.readObject();
			} else {
				return new ArrayList<>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void save(List<Emp> empList) {
		try {
			File file = new File(empserPath);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(empList);
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//
}
