package com.ziheng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ziheng.dao.CourseAttendanceInfoMapper;
import org.springframework.stereotype.Service;

import com.ziheng.domain.CourseAttendanceInfo;
import com.ziheng.service.CourseAttendanceInfoService;
import com.sun.DateUtils;







/**
 *  @author ziheng.com
 *
 */
@Service
public class CourseAttendanceInfoServiceImpl implements CourseAttendanceInfoService {

	@Resource
	private CourseAttendanceInfoMapper courseAttendanceMapper;


	@Override
	public void insert(CourseAttendanceInfo o) {
		// TODO Auto-generated method stub
		o.setCts(DateUtils.getNowDateTimeString());

		courseAttendanceMapper.insert(o);
	}

	@Override
	public void update(CourseAttendanceInfo o) {
		// TODO Auto-generated method stub
		courseAttendanceMapper.update(o);
	}

	@Override
	public CourseAttendanceInfo getById(Integer id) {
		// TODO Auto-generated method stub
		return courseAttendanceMapper.getById(id);
	}

	@Override
	public List<CourseAttendanceInfo> list(CourseAttendanceInfo o) {
		// TODO Auto-generated method stub
		return courseAttendanceMapper.list(o);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		courseAttendanceMapper.delete(id);
	}







}
