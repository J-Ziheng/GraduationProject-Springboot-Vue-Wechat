package com.ziheng.service.impl;

import com.ziheng.dao.ApplyMapper;
import com.ziheng.domain.Apply;
import com.ziheng.service.ApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 *  @author ziheng.com
 *
 */
@Service
public class ApplyServiceImpl implements ApplyService {

	@Resource
	private ApplyMapper applyMapper;

	@Override
	public void insert(Apply o) {
		// TODO Auto-generated method stub
		applyMapper.add(o);
	}

	@Override
	public void update(Apply o) {
		// TODO Auto-generated method stub
		applyMapper.update(o);
	}


	public Apply getById(Integer id) {
		// TODO Auto-generated method stub
		return applyMapper.getById(id);
	}

	@Override
	public List<Apply> list(Apply o) {
		// TODO Auto-generated method stub
		return applyMapper.list(o);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		applyMapper.delete(id);
	}

 

	 
	 

	 
}
