package com.ziheng.service;

import java.util.List;

import com.ziheng.domain.Teacher;


public interface TeacherService {
    Teacher getByTeachNo(String teach_no);

    void insert(Teacher o);

    void update(Teacher o);

    Teacher getById(String id);
//    Teacher getById(Integer id);

    List<Teacher> list(Teacher o);


    void delete(String id);

    /**
     * 查询课程course绑定的教师 返回集合 web端
     * @return
     */
    List<Teacher> selectRealname();

    /**
     * 查询学生信息stu_attendance绑定的教师 小程序端
     * @return
     */
    List<Teacher> selectTeachername();
}
