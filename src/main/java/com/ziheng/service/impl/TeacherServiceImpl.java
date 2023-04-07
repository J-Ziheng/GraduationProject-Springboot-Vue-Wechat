package com.ziheng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ziheng.dao.TeachMapper;
import com.ziheng.domain.Teacher;
import com.ziheng.service.TeacherService;
/**
 *  @author ziheng.com
 *
 */
@Service
public class TeacherServiceImpl implements TeacherService {

	@Resource
	private TeachMapper teachMapper;
	@Override
	public Teacher getByTeachNo(String teach_no) {
		List<Teacher> li= teachMapper.getByTeachNo(teach_no);
		if(li!=null&&li.size()>0)return li.get(0);
		else return null;
	}

	@Override
	public void insert(Teacher o) {
		teachMapper.insert(o);
	}

	@Override
	public void update(Teacher o) {
		teachMapper.update(o);
	}

	/**
	 * 根据id查询教师
	 * @param id
	 * @return
	 */
//	@Override
//	public Teacher getById(Integer id) {
//		return teachMapper.getById(id);
//	}

	@Override
	public Teacher getById(String id) {
		return teachMapper.getById(id);
	}

	@Override
	public List<Teacher> list(Teacher o) {
		return teachMapper.list(o);
	}

	@Override
	public void delete(String id) {
		teachMapper.delete(id);
	}

	/**
	 * 查询课程course绑定的教师 返回list集合 web端
	 * @return
	 */
	@Override
	public List<Teacher> selectRealname() {
		return teachMapper.selectRealname();
	}

	/**
	 * 查询学生信息stu_attendance绑定的教师 返回list集合 小程序端
	 * @return
	 */
	@Override
	public List<Teacher> selectTeachername() {
		return teachMapper.selectTeachername();
	}

}
