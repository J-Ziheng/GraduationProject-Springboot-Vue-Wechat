package com.ziheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.ziheng.domain.Course;

/**
 * 课程
 */
@Mapper
public interface CourseMapper {
	
	
	@Select("select c.* ,m.major_name,t.realname  from course c left join major m on  m.id=c.major_id left join student t on t.id=c.teach_id where c.id = #{0}")
    Course getById(String  id);
	
	@Insert("insert  into course (course_name,major_id,teach_id,has_stu,has_theme) values (#{course_name},#{major_id},#{teach_id},'0','0') ")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	void insert(Course o);
	
	
	@Update("<script>update course "
			+"<trim prefix=\"set\" suffixOverrides=\",\">"
			+"<if test=\"course_name != null and course_name != ''\">course_name = #{course_name},</if>"
			+"<if test=\"major_id != null and major_id != ''\">major_id = #{major_id},</if>"
			+"<if test=\"teach_id != null and teach_id != ''\">teach_id = #{teach_id},</if>"
			+"<if test=\"has_stu != null and has_stu != ''\">has_stu = #{has_stu},</if>"
			+"<if test=\"has_theme != null and has_theme != ''\">has_theme = #{has_theme},</if>"
			+"</trim> where id=#{id}</script>")
    void update(Course o);
	
	
	@Select("<script>select c.* ,m.major_name,t.realname  from course c left join major m on  m.id=c.major_id left join student t on t.id=c.teach_id where 1=1"
			+"<if test=\"course_name != null and course_name !='' \"> and  course_name  like concat('%',#{course_name},'%') </if>"
			+"<if test=\"major_id != null and major_id != ''\">and major_id = #{major_id}</if>"
			+"<if test=\"teach_id != null and teach_id != ''\">and teach_id = #{teach_id}</if>"
			+"<if test=\"has_stu != null and has_stu != ''\">and has_stu = #{has_stu}</if>"
			+"<if test=\"has_theme != null and has_theme != ''\">and has_theme = #{has_theme}</if>"
			+"</script>")
    List<Course> list(Course o);
	
	
	@Delete("delete from course where id = #{0}")
	void delete(String id);
	 
}