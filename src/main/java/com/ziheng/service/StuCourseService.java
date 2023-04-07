package com.ziheng.service;

import java.util.List;

import com.ziheng.domain.StuCourse;


public interface StuCourseService {

	/**
	 * 插入课程学生信息
	 * @param o
	 */
    void insert(StuCourse o);

	/**
	 * 根据课程id统计学生数量
	 * @param course_id
	 * @return
	 */
   Integer getCountByCourseId(Integer course_id);

	/**
	 * 根据学生更新课程
	 * @param o
	 */
    void update(StuCourse o);

	/**
	 * 根据id查找课程学生信息
	 * @param id
	 * @return
	 */
    StuCourse getById(String id);

	/**
	 * 查询课程学生信息
	 * @param o
	 * @return
	 */
   List<StuCourse> list(StuCourse o);

	/**
	 * 删除课程学生信息
	 * @param id
	 */
   void delete(String id);


//    public Long getStuNum(String course_id);
}
