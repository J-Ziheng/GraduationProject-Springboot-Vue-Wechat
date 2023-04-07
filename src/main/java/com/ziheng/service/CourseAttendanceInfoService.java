package com.ziheng.service;

import java.util.List;

import com.ziheng.domain.CourseAttendanceInfo;


public interface CourseAttendanceInfoService {

	/**
	 * 插入课程考勤信息
	 * @param o
	 */
    void insert(CourseAttendanceInfo o);

	/**
	 * 更新课程考勤信息
	 * @param o
	 */
    void update(CourseAttendanceInfo o);

	/**
	 * 根据id获取课程考勤及考勤课程名和教师名信息
	 * @param id
	 * @return
	 */
    CourseAttendanceInfo getById(Integer id);

	/**
	 * 查询课程考勤信息
	 * @param o
	 * @return
	 */
    List<CourseAttendanceInfo> list(CourseAttendanceInfo o);

	/**
	 * 删除课程考勤信息
	 * @param id
	 */
    void delete(Integer id);
}
