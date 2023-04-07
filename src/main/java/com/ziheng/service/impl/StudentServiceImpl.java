package com.ziheng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ziheng.dao.StudentMapper;
import com.ziheng.domain.Student;
import com.ziheng.service.StudentService;

 

 
 

/**
 *  @author ziheng.com
 *
 */
@Service
public class StudentServiceImpl implements StudentService {

	@Resource
	private StudentMapper studentMapper;
	@Override
	public Student getByStuNo(String teach_no) {
		// TODO Auto-generated method stub
		List<Student> li= studentMapper.getByStuNo(teach_no);
		if(li!=null&&li.size()>0)return li.get(0);
		else return null;
	}

	@Override
	public void insert(Student o) {
		// TODO Auto-generated method stub
		studentMapper.insert(o);
	}

	@Override
	public void update(Student o) {
		// TODO Auto-generated method stub
		studentMapper.update(o);
	}

	@Override
	public Student getById(String id) {
		// TODO Auto-generated method stub
		return studentMapper.getById(id);
	}

	@Override
	public List<Student> list(Student o) {
		// TODO Auto-generated method stub
		return studentMapper.list(o);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		studentMapper.delete(id);
	}

	@Override
	public Student getByToken(String token) {
		// TODO Auto-generated method stub
		Student s=new Student();
		s.setToken(token);
		List<Student> sli= studentMapper.list(s);
		if(sli!=null&&!sli.isEmpty())return sli.get(0);
		return null;
	}

 

	 
	 

	 
}
