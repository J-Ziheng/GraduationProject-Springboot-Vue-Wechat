package com.ziheng.service;

import java.util.List;
import com.ziheng.domain.Course;


public interface CourseService {

    /**
     * 插入课程信息
     * @param o
     */
    void insert(Course o);

    /**
     * 更新课程信息
     * @param o
     */
    void update(Course o);

    /**
     * 根据课程id查找课程信息
     * @param id
     * @return
     */
    Course getById(String id);

    /**
     * 查询课程信息
     * @param o
     * @return
     */
    List<Course> list(Course o);

    /**
     * 删除课程信息
     * @param id
     */
    void delete(String id);
}
