package com.ziheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ziheng.domain.CourseAttendanceInfo;

/**
 * 课程考勤信息
 */
@Mapper
public interface CourseAttendanceInfoMapper {



	@Select("select a.*,c.course_name,t.realname as teach_realname,m.major_name "
			+ "from course_attendance a "
			+ "left join course c on c.id=a.course_id "
			+ "left join student t on t.id=a.teach_id "
			+ "left join major m on m.id=c.major_id "
			+ "where a.id = #{0}")
    CourseAttendanceInfo getById(Integer  id);

	@Insert("insert into course_attendance (date_,start_ts,end_ts,course_id,has_attendance,cts,come_num,all_num,not_come_num,teach_id,location,lat,lng,dk_range,room,qj_num,late_num,normal_come_num,wait_come_num) values" +
			" (#{date_},#{start_ts},#{end_ts},#{course_id},#{has_attendance},#{cts},#{come_num},#{all_num},#{not_come_num },#{teach_id},#{location},#{lat},#{lng},#{dk_range},#{room},#{qj_num},#{late_num},#{normal_come_num},#{wait_come_num})")
	void insert(CourseAttendanceInfo o);


	@Update("<script>update course_attendance "
			+"<trim prefix=\"set\" suffixOverrides=\",\">"
			+"<if test=\"date_ != null and date_ != ''\">date_ = #{date_},</if>"
			+"<if test=\"start_ts != null and start_ts != ''\">start_ts = #{start_ts},</if>"
			+"<if test=\"end_ts != null and end_ts != ''\">end_ts = #{end_ts},</if>"
			+"<if test=\"course_id != null and course_id !=''  \">course_id = #{course_id},</if>"
			+"<if test=\"teach_id != null  \">teach_id = #{teach_id},</if>"
			+"<if test=\"has_attendance != null and has_attendance != ''\">has_attendance = #{has_attendance},</if>"
			+"<if test=\"come_num != null \">come_num = #{come_num},</if>"
			+"<if test=\"all_num != null \">all_num = #{all_num},</if>"
			+"<if test=\"not_come_num != null \">not_come_num = #{not_come_num},</if>"
			+"<if test=\"location != null and location != ''\">location = #{location},</if>"
			+"<if test=\"lat != null and lat != ''\">lat = #{lat},</if>"
			+"<if test=\"lng != null and lng != ''\">lng = #{lng},</if>"
			+"<if test=\"room != null and room != ''\">room = #{room},</if>"
			+"<if test=\"dk_range != null \">dk_range = #{dk_range},</if>"
			+"<if test=\"qj_num != null \">qj_num = #{qj_num},</if>"
			+"<if test=\"late_num != null \">late_num = #{late_num},</if>"
			+"<if test=\"normal_come_num != null \">normal_come_num = #{normal_come_num},</if>"
			+"<if test=\"wait_come_num != null \">wait_come_num = #{wait_come_num},</if>"
			+"</trim> where id=#{id}</script>")
    void update(CourseAttendanceInfo o);


	@Select("<script>select a.*,c.course_name,t.realname as teach_realname,m.major_name "
			+ "from course_attendance a "
			+ "left join course c on c.id=a.course_id "
			+ "left join student t on t.id=a.teach_id "
			+ "left join major m on m.id=c.major_id "
			+ "where 1=1 "
			+"<if test=\"course_name != null and course_name !=''  \"> and  c.course_name like concat('%',#{course_name},'%')  </if>"
			+"<if test=\"date_ != null and date_ !=''  \"> and  a.date_ like concat('%',#{date_},'%')  </if>"
			+"<if test=\"teach_id != null   \"> and  a.teach_id = #{teach_id} </if>"
			+"<if test=\"course_id != null and course_id !=''  \"> and  a.course_id = #{course_id} </if>"
			+"</script>")
    List<CourseAttendanceInfo> list(CourseAttendanceInfo o);


	@Delete("delete from course_attendance where id = #{0}")
	void delete(Integer id);




}
