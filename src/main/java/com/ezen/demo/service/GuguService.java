package com.ezen.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GuguService {
	public List<String> createGugu(int dan) {
		List<String> list = new ArrayList<>();
		for (int i=1; i<=9; i++) {
			String line = String.format("%d * %d = %d", dan,i,dan*i);
			list.add(line);
		}
		return list;
	}
}
