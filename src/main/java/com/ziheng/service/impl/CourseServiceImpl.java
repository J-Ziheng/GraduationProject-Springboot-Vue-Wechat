package com.ziheng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ziheng.dao.CourseMapper;
import com.ziheng.domain.Course;
import com.ziheng.service.CourseService;

 

 
 

/**
 *  @author ziheng.com
 *
 */
@Service
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseMapper courseMapper;

	@Override
	public void insert(Course o) {
		courseMapper.insert(o);
	}

	@Override
	public void update(Course o) {
		courseMapper.update(o);
	}

	@Override
	public Course getById(String id) {
		return courseMapper.getById(id);
	}

	@Override
	public List<Course> list(Course o) {
		return courseMapper.list(o);
	}

	@Override
	public void delete(String id) {
		courseMapper.delete(id);
	}

 

	 
	 

	 
}
