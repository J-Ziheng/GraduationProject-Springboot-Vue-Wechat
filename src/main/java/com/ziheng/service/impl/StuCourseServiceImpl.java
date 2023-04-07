package com.ziheng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ziheng.dao.StuCourseMapper;
import com.ziheng.domain.StuCourse;
import com.ziheng.service.StuCourseService;

 

 
 

/**
 *  @author ziheng.com
 *
 */
@Service
public class StuCourseServiceImpl implements StuCourseService {

	@Resource
	private StuCourseMapper stuCourseMapper;

	@Override
	public void insert(StuCourse o) {
		// TODO Auto-generated method stub
		stuCourseMapper.insert(o);
	}

	@Override
	public void update(StuCourse o) {
		// TODO Auto-generated method stub
		stuCourseMapper.update(o);
	}

	@Override
	public StuCourse getById(String id) {
		// TODO Auto-generated method stub
		return stuCourseMapper.getById(id);
	}

	@Override
	public List<StuCourse> list(StuCourse o) {
		// TODO Auto-generated method stub
		return stuCourseMapper.list(o);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		stuCourseMapper.delete(id);
	}

	@Override
	public Integer getCountByCourseId(Integer course_id) {
		// TODO Auto-generated method stub
		return stuCourseMapper.getCountByCourseId(course_id);
	}


	//	@Override
//	public Long getStuNum(String course_id) {
//		// TODO Auto-generated method stub
//		return etCourseStuMapper.getStuNum(course_id);
//	}

 

	 
	 

	 
}
