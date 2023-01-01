package com.ezen.demo.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(exclude={"num","title","contents","author"})
@AllArgsConstructor
@NoArgsConstructor
public class Board implements Comparable<Board> {
	private int num;
	private String title;
	private String contents;
	private String author;
	private List<Attach> liAttach = new ArrayList<>();
	@Override
	public int compareTo(Board o) {
		if (this.getNum() > o.getNum()) return 1;
		if (this.getNum() < o.getNum()) return -1;
		return 0;
	}
}
