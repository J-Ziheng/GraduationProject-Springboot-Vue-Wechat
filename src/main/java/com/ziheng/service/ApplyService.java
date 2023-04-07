package com.ziheng.service;

import com.ziheng.domain.Apply;

import java.util.List;


public interface ApplyService {
		
	 void insert(Apply o);
	 
	 void update(Apply o);

	 Apply getById(Integer id);

	 List<Apply> list(Apply o);

	 void delete(Integer id);
}
