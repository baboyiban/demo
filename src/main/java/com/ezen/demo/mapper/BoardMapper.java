package com.ezen.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.demo.vo.Attach;
import com.ezen.demo.vo.Board;

@Mapper
public interface BoardMapper {
	// get
	public List<Map<String,Object>> getListBoardAndAttach();
	// |-Board	
	public List<Board> getListBoard();
	public Board getBoard(int num);
	// |-Attach
	public List<Attach> getListAttach(int bnum);
	public Attach getAttach(int num);
	// set
	public int saveBoard(Board b);
	public int saveAttach(List<Attach> list);
}
