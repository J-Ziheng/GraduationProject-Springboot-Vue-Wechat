package com.ziheng.service;

import java.util.List;

import com.ziheng.domain.StuAttendanceInfo;

public interface StuAttendanceInfoService {
	void deleteByAttId(Integer att_id);

	void insert(StuAttendanceInfo o);

	Integer getByCount(StuAttendanceInfo o);

	void update(StuAttendanceInfo o);

	void updateAbsenteeism(String today_date,String today_hm);

	List<StuAttendanceInfo> getAbsenteeism(String today_date, String today_hm);

	StuAttendanceInfo getById(String  id);

	List<StuAttendanceInfo> list(StuAttendanceInfo o);

	void delete(String id);
}
