package com.ziheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ziheng.domain.StuCourse;

/**
 * 学生课程
 */
@Mapper
public interface StuCourseMapper {


	@Select("select cs.*,s.stu_no,s.realname,s.sex,m.major_name,m.id as major_id,sy.sys_name ,sy.id as sys_id,c.course_name " +
			"from stu_course cs " +
			"left join student s on s.id=cs.stu_id " +
			"left join major m on s.major_id=m.id " +
			"left join academy sy on s.sys_id=sy.id   " +
			"left join course c on c.id=cs.course_id   " +
			" where   cs.id = #{0}")
    StuCourse getById(String  id);


	@Select("select count(id) from stu_course where course_id = #{0}")
    Integer getCountByCourseId(Integer  course_id);

	@Insert("insert into stu_course (stu_id,course_id) values (#{stu_id},#{course_id}) ")
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
	void insert(StuCourse o);


	@Update("<script>update stu_course "
			+"<trim prefix=\"set\" suffixOverrides=\",\">"
			+"<if test=\"stu_id != null and stu_id != ''\">stu_id = #{stu_id},</if>"
			+"<if test=\"course_id != null and course_id != ''\">course_id = #{course_id},</if>"
			+"</trim> where id=#{id}</script>")
    void update(StuCourse o);


	@Select("<script>select cs.*,s.stu_no,s.realname,s.sex,m.major_name,m.id as major_id,sy.sys_name ,sy.id as sys_id,c.course_name "
			+ "from stu_course cs "
			+ "left join student s on s.id=cs.stu_id "
			+ "left join major m on s.major_id=m.id "
			+ "left join academy sy on s.sys_id=sy.id   "
			+ "left join course c on c.id=cs.course_id   "
			+ "where 1=1"
			+"<if test=\"stu_id != null and stu_id != ''\"> and stu_id = #{stu_id}</if>"
			+"<if test=\"course_id != null and course_id != ''\"> and course_id = #{course_id}</if>"
			+"<if test=\"realname != null and realname != ''\"> and realname like concat('%',#{realname},'%') </if>"
			+"<if test=\"major_id != null and major_id != ''\"> and major_id = #{major_id}</if>"
			+"<if test=\"sys_id != null and sys_id != ''\"> and sys_id = #{sys_id}</if>"
			+"</script>")
    List<StuCourse> list(StuCourse o);


	@Delete("delete from stu_course where id = #{0}")
	void delete(String id);


//	@Select("select count(id) from stu_course where  course_id = #{0}")
//  Long getStuNum(String course_id);

}
