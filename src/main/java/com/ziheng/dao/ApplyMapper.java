package com.ziheng.dao;

import com.ziheng.domain.Apply;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 请假申请
 */
@Mapper
public interface ApplyMapper {

	@Insert("insert into apply (stuid,stu_name,stu_no,apply_time,apply_content,teachid,teach_name,course_attendanceid,stu_att_id,status,result_time,stu_major) " +
			"values(#{stuid},#{stu_name},#{stu_no},#{apply_time},#{apply_content},#{teachid},#{teach_name},#{course_attendanceid},#{stu_att_id},#{status},#{result_time},#{stu_major})")
	@Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
	void add(Apply u);


	@Select("<script>select a.*, " +
			"c.course_name  ," +
			"b.start_ts as att_stime,b.end_ts as att_etime,b.date_,b.room " +
			"  from apply a " +
			"left join course_attendance b  on a.course_attendanceid = b.id " +
			"left join course c  on b.course_id = c.id where 1=1  "
			+ "<if test=\"stuid != null   \"> and  a.stuid = #{stuid}  </if> "
			+ "<if test=\"stu_no != null  and stu_no !='' \"> and  a.stu_no = #{stu_no}  </if> "
			+ "<if test=\"stu_name != null  and stu_name !='' \"> and  a.stu_name = #{stu_name}  </if> "
			+ "<if test=\"teachid != null   \"> and  a.teachid = #{teachid}  </if> "
			+ "<if test=\"teach_name != null and teach_name !=''  \"> and  a.teach_name   like concat('%',#{teach_name},'%')  </if> "
			+ "<if test=\"course_name != null and course_name !=''  \"> and  c.course_name   like concat('%',#{course_name},'%') </if> "
			+ "<if test=\"course_attendanceid != null  \"> and a.course_attendanceid = #{course_attendanceid}  </if> "
			+ "<if test=\"status != null  \"> and  a.status = #{status}  </if> "
			+ "</script>")
	List<Apply> list(Apply u);
	
	
	@Select("select a.*, " +
			"c.course_name ," +
			"b.start_ts as att_stime,b.end_ts as att_etime,b.date_,b.room" +
			" from apply a left join course_attendance b  on a.course_attendanceid = b.id left join course c  on b.course_id = c.id  where a.id=#{id}")
	Apply getById(Integer id);
	
	
	
	@Update("<script>update apply "
			+"<trim prefix=\"set\" suffixOverrides=\",\">"
			+"<if test=\"teach_name != null and teach_name != ''\">teach_name = #{teach_name},</if>"
			+"<if test=\"result_time != null and result_time != ''\">result_time = #{result_time},</if>"
			+"<if test=\"teachid != null \">teachid = #{teachid},</if>"
			+"<if test=\"status != null \">status = #{status},</if>"
			+"</trim> where id=#{id}"
			+ " </script>")
	void update(Apply u);


	@Delete("delete from apply where id=#{id}")
	void delete(Integer id);
}
