package com.ziheng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ziheng.dao.MajorMapper;
import com.ziheng.domain.Major;
import com.ziheng.service.MajorService;

 

 
 

/**
 *  @author ziheng.com
 *
 */
@Service
public class MajorServiceImpl implements MajorService {

	@Resource
	private MajorMapper majorMapper;

	@Override
	public void insert(Major o) {
		majorMapper.insert(o);
	}

	@Override
	public void update(Major o) {
		majorMapper.update(o);
	}

	@Override
	public Major getById(String id) {
		return majorMapper.getById(id);
	}

	/**
	 * 查询所有专业
	 * @param o
	 * @return
	 */
	@Override
	public List<Major> list(Major o) {
		return majorMapper.list(o);
	}

	@Override
	public void delete(String id) {
		majorMapper.delete(id);
	}

 

	 
	 

	 
}
