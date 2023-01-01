package com.ezen.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(exclude= {"num","bnum","fname","fsize"})
@AllArgsConstructor
@NoArgsConstructor
public class Attach implements Comparable<Attach> {
	private int num;
	private int bnum;
	private String fname;
	private long fsize;
	@Override
	public int compareTo(Attach o) {
		if (this.getBnum() > o.getBnum()) return 1;
		if (this.getBnum() < o.getBnum()) return -1;
		return 0;
	}
}
