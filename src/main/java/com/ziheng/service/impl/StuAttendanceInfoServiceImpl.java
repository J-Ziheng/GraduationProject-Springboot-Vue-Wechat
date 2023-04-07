package com.ziheng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ziheng.dao.StuAttendanceInfoMapper;
import com.ziheng.domain.StuAttendanceInfo;
import com.ziheng.service.StuAttendanceInfoService;







/**
 * 学生考勤记录
 *  @author ziheng.com
 *
 */
@Service
public class StuAttendanceInfoServiceImpl implements StuAttendanceInfoService {

	@Resource
	private StuAttendanceInfoMapper stuAttendanceInfoMapper;


	@Override
	public void insert(StuAttendanceInfo o) {
		stuAttendanceInfoMapper.insert(o);
	}

	@Override
	public void update(StuAttendanceInfo o) {
		stuAttendanceInfoMapper.update(o);
	}

	@Override
	public void updateAbsenteeism(String today_date,String today_hm) {
		stuAttendanceInfoMapper.updateAbsenteeism(today_date,today_hm);
	}

	/**
	 * 获取所有考勤日期 小程序首页课程考勤 进行展示
	 * @param today_date
	 * @param today_hm
	 * @return
	 */
	@Override
	public List<StuAttendanceInfo> getAbsenteeism(String today_date, String today_hm) {
		return stuAttendanceInfoMapper.getAbsenteeism(today_date,today_hm);
	}

	@Override
	public StuAttendanceInfo getById(String id) {
		return stuAttendanceInfoMapper.getById(id);
	}

	@Override
	public List<StuAttendanceInfo> list(StuAttendanceInfo o) {
		return stuAttendanceInfoMapper.list(o);
	}

	@Override
	public void delete(String id) {
		stuAttendanceInfoMapper.delete(id);
	}

	@Override
	public void deleteByAttId(Integer att_id) {
		stuAttendanceInfoMapper.deleteByAttId(att_id);
	}

	@Override
	public Integer getByCount(StuAttendanceInfo o) {
		return stuAttendanceInfoMapper.getByCount(o);
	}
}
