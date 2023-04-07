package com.ziheng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ziheng.dao.AcademyMapper;
import com.ziheng.domain.Academy;
import com.ziheng.service.AcademyService;

 

 
 

/**
 *  @author ziheng.com
 *
 */
@Service
public class AcademyServiceImpl implements AcademyService {

	@Resource
	private AcademyMapper academyMapper;

	@Override
	public void insert(Academy o) {
		// TODO Auto-generated method stub
		academyMapper.insert(o);
	}

	@Override
	public void update(Academy o) {
		// TODO Auto-generated method stub
		academyMapper.update(o);
	}

	@Override
	public Academy getById(String id) {
		// TODO Auto-generated method stub
		return academyMapper.getById(id);
	}

	@Override
	public List<Academy> list(Academy o) {
		// TODO Auto-generated method stub
		return academyMapper.list(o);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		academyMapper.delete(id);
	}

 

	 
	 

	 
}
