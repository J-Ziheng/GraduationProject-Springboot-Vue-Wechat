package com.ziheng.service;

import java.util.List;

import com.ziheng.domain.Major;


 

public interface MajorService {
		
	 void insert(Major o);
	 
	 void update(Major o);

	 Major getById(String  id);

	 List<Major> list(Major o);

	 void delete(String id);
}
