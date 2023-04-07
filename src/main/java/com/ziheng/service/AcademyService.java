package com.ziheng.service;

import java.util.List;

import com.ziheng.domain.Academy;


 
//AcademyService
public interface AcademyService {

	void insert(Academy o);

	void update(Academy o);
	Academy getById(String  id);

	List<Academy> list(Academy o);

	void delete(String id);
}
