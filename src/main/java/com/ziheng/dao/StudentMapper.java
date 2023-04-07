package com.ziheng.dao;

import java.util.List;

import com.ziheng.domain.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


/**
 * 学生
 */
@Mapper
public interface StudentMapper {

	@Select("select * from student where stu_no = #{0}")
	List<Student> getByStuNo(String  stu_no);

	@Select("select * from student where id = #{0}")
	Student getById(String  id);

	@Insert("insert into student (upass,realname,stu_no,sex,sys_id,major_id,photo,token,is_face) values (#{upass},#{realname},#{stu_no},#{sex},#{sys_id},#{major_id},#{photo},#{token},#{is_face}) ")
	void insert(Student o);


	@Update("<script>update student "
			+"<trim prefix=\"set\" suffixOverrides=\",\">"
			+"<if test=\"realname != null and realname != ''\">realname = #{realname},</if>"
			+"<if test=\"stu_no != null and stu_no != ''\">stu_no = #{stu_no},</if>"
			+"<if test=\"sex != null and sex != ''\">sex = #{sex},</if>"
			+"<if test=\"upass != null and upass != ''\">upass = #{upass},</if>"
			+"<if test=\"sys_id != null and sys_id != ''\">sys_id = #{sys_id},</if>"
			+"<if test=\"major_id != null and major_id != ''\">major_id = #{major_id},</if>"
			+"<if test=\"photo != null and photo != ''\">photo = #{photo},</if>"
			+"<if test=\"token != null and token != ''\">token = #{token},</if>"
			+"<if test=\"is_face != null and is_face != ''\">is_face = #{is_face},</if>"
			+"<if test=\"face_data != null  \">face_data = #{face_data},</if>"
			+"</trim> where id=#{id}</script>")
	void update(Student o);

	@Select("<script>select s.*,y.sys_name,m.major_name from student s left join academy y on y.id=s.sys_id "
			+ "left join major m on m.id=s.major_id where 1=1"
			+"<if test=\"realname != null and realname !='' \"> and  realname  like concat('%',#{realname},'%') </if>"
			+"<if test=\"token != null and token !='' \"> and  token = #{token} </if>"
			+"<if test=\"stu_no != null and stu_no !='' \"> and  stu_no = #{stu_no} </if>"
			+"<if test=\"sys_id != null and sys_id !='' \"> and  sys_id = #{sys_id} </if>"
			+"<if test=\"major_id != null and major_id !='' \"> and  major_id = #{major_id} </if>"
			+"<if test=\"upass != null and upass !='' \"> and  upass = #{upass} </if>"
			+"</script>")
	List<Student> list(Student o);


	@Delete("delete from student where id = #{0}")
	void delete(String id);

}