package com.ziheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.ziheng.domain.Teacher;

/**
 * 教室
 */
@Mapper
public interface TeachMapper {
	// #{0} --> #{teach_no}
	@Select("select * from teacher where teach_no =#{0}")
    List<Teacher> getByTeachNo(String  teach_no);

	// #{0} --> #{id}
	@Select("select * from teacher where id = #{0}")
    Teacher getById(String  id);
	
	@Insert("insert into teacher (upass,realname,teach_no,sex) values (#{upass},#{realname},#{teach_no},#{sex})")
	void insert(Teacher o);
	
	
	@Update("<script>update teacher "
			+"<trim prefix=\"set\" suffixOverrides=\",\">"
			+"<if test=\"realname != null and realname != ''\">realname = #{realname},</if>"
			+"<if test=\"teach_no != null and teach_no != ''\">teach_no = #{teach_no},</if>"
			+"<if test=\"sex != null and sex != ''\">sex = #{sex},</if>"
			+"<if test=\"upass != null and upass != ''\">upass = #{upass},</if>"
			+"</trim> where id=#{id}</script>")
    void update(Teacher o);
	
	
	@Select("<script>select * from teacher where 1=1"
			+"<if test=\"realname != null and realname !='' \"> and  realname like concat('%',#{realname},'%')  </if>"
			+"<if test=\"teach_no != null and teach_no !='' \"> and  teach_no = #{teach_no} </if>"
			+"<if test=\"upass != null and upass !='' \"> and  upass = #{upass} </if>"
			+"</script>")
    List<Teacher> list(Teacher o);
	
	
	@Delete("delete from teacher where id = #{0}")
	void delete(String id);

	/**
	 * TODO 底层修复bug ： 查询课程course绑定的教师，供上层注入（web端）
	 * @return
	 */
	@Select("select realname from teacher,course where course.teach_id = teacher.id;")
	List<Teacher> selectRealname();

	/**
	 * TODO 底层修复bug ： 查询学生信息stu_attendance绑定的教师，供上层注入（小程序端）
	 */
	@Select("select realname from teacher,stu_attendance where stu_attendance.teach_id = teacher.id;")
	List<Teacher> selectTeachername();
}