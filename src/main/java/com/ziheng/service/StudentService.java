package com.ziheng.service;

import java.util.List;

import com.ziheng.domain.Student;


public interface StudentService {

    Student getByStuNo(String teach_no);

    void insert(Student o);

    void update(Student o);

    Student getById(String id);

    List<Student> list(Student o);

    void delete(String id);

    Student getByToken(String token);
}
