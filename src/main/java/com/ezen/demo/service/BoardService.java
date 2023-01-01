package com.ezen.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.demo.mapper.BoardMapper;
import com.ezen.demo.vo.Attach;
import com.ezen.demo.vo.Board;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BoardService {
	@Autowired
	private BoardMapper dao;
	@Autowired
	private ResourceLoader resourceLoader;

	// list
	public List<Map<String,Object>> getList() {
		List<Map<String,Object>> list = dao.getListBoardAndAttach();
		return list;
	}
	// board
	public List<Board> getListBoard() {
		List<Board> listBoard = dao.getListBoard();
		return listBoard;
	}

	public Board getBoard(int num) {
		Board board = dao.getBoard(num);
		return board;
	}

	// save
	public String saveBoardFiles(HttpServletRequest request, Board b, MultipartFile[] mfs) {
		// value
		String result = "저장 실패";
		int saveBoard = 0;
		List<Attach> listAttach = null;
		int saveAttach = 0;
		String savePath = request.getServletContext().getRealPath("/WEB-INF/files");

		saveBoard = dao.saveBoard(b);
		listAttach = new ArrayList<>();
		try {
			for (int i = 0; i < mfs.length; i++) {
				mfs[i].transferTo(new File(savePath + "/" + mfs[i].getOriginalFilename()));
				// list
				Attach a = new Attach();
				a.setFname(mfs[i].getOriginalFilename());
				a.setFsize(mfs[i].getSize());
				listAttach.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		saveAttach = dao.saveAttach(listAttach);
		if (saveBoard > 0 && saveAttach > 0) {
			result = "저장 성공";
		}

		return result;
	}

	// attach
	public List<Attach> getListAttach(int num) {
		List<Attach> listAttach = dao.getListAttach(num);
		return listAttach;
	}

	// file
	public ResponseEntity<Resource> getResource(HttpServletRequest request, int num) {
		Attach attach = dao.getAttach(num);
		Resource resource = resourceLoader.getResource("WEB-INF/files/" + attach.getFname());
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
	}
}
