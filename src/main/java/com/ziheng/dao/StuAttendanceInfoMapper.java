package com.ziheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ziheng.domain.StuAttendanceInfo;

/**
 * 学生课程考勤
 */
@Mapper
public interface StuAttendanceInfoMapper {

	@Select("select  sa.*," +
			"t.realname as teach_realname," +
			"s.realname as stu_realname,s.stu_no,s.photo,s.is_face,s.face_data," +
			"c.course_name,m.major_name " +
			" from stu_attendance sa "
			+ "left join student t on t.id=sa.teach_id "
			+ "left join student s on s.id=sa.stu_id "
			+ "left join course c on c.id=sa.course_id "
			+ "left join major m on m.id=s.major_id "
			+ "where  sa.id = #{0}")
    StuAttendanceInfo getById(String  id);


	@Select("<script> select  count(id)  from stu_attendance  where 1=1 "
			+"<if test=\"att_id != null   \"> and att_id  = #{att_id}   </if>"
			+"<if test=\"stu_id != null   \"> and stu_id  = #{stu_id}   </if>"
			+"<if test=\"course_id != null   \"> and course_id  = #{course_id}   </if>"
			+"<if test=\"status_ != null and  status_ !='' \"> and status_  = #{status_}   </if> "
			+" </script>")
    Integer getByCount(StuAttendanceInfo o);



	@Insert("insert into stu_attendance (att_id,stu_id,course_id,teach_id,date_,start_ts,cts,status_,type,is_late,course_start_ts,course_end_ts,location,lat,lng,dk_range,room ) values" +
			" (#{att_id},#{stu_id},#{course_id},#{teach_id},#{date_},#{start_ts},#{cts},#{status_},#{type},#{is_late},#{course_start_ts},#{course_end_ts },#{location},#{lat},#{lng},#{dk_range},#{room})")
	void insert(StuAttendanceInfo o);


	@Update("<script>update stu_attendance "
			+"<trim prefix=\"set\" suffixOverrides=\",\">"
			+"<if test=\"att_id != null \">att_id = #{att_id},</if>"
			+"<if test=\"stu_id != null \">stu_id = #{stu_id},</if>"
			+"<if test=\"course_id != null \">course_id = #{course_id},</if>"
			+"<if test=\"teach_id != null \">teach_id = #{teach_id},</if>"
			+"<if test=\"date_ != null and date_ != ''\">date_ = #{date_},</if>"
			+"<if test=\"start_ts != null and start_ts != ''\">start_ts = #{start_ts},</if>"
			+"<if test=\"status_ != null and status_ != ''\">status_ = #{status_},</if>"
			+"<if test=\"apply_state != null and apply_state != ''\">apply_state = #{apply_state},</if>"
			+"<if test=\"type != null and type != ''\">type = #{type},</if>"
			+"<if test=\"is_late != null and is_late != ''\">is_late = #{is_late},</if>"
			+"<if test=\"cts   != null and cts != ''\">cts = #{cts},</if>"
			+"<if test=\"course_start_ts   != null and course_start_ts != ''\">course_start_ts = #{course_start_ts},</if>"
			+"<if test=\"course_end_ts != null and course_end_ts != ''\">course_end_ts = #{course_end_ts},</if>"
			+"<if test=\"location != null and location != ''\">location = #{location},</if>"
			+"<if test=\"lat != null and lat != ''\">lat = #{lat},</if>"
			+"<if test=\"lng != null and lng != ''\">lng = #{lng},</if>"
			+"<if test=\"room != null and room != ''\">room = #{room},</if>"
			+"<if test=\"dk_range != null \">dk_range = #{dk_range},</if>"
			+"</trim> where id=#{id}</script>")
    void update(StuAttendanceInfo o);

	@Update("<script>update stu_attendance set status_='4' where (date_  &lt; #{0} or (date_=#{0} and course_end_ts &lt; #{1})) and status_='0' </script>")
    void updateAbsenteeism(String today_date,String today_hm);

	@Select("<script>select *  from stu_attendance  where    (date_  &lt; #{0} or (date_=#{0} and course_end_ts &lt; #{1})) and status_='0'</script>")
	List<StuAttendanceInfo> getAbsenteeism(String today_date, String today_hm);

	@Select("<script> select sa.*,t.realname as teach_realname,"
			+ " s.realname as stu_realname,s.stu_no,s.photo,s.is_face,s.face_data,"
			+ " c.course_name,m.major_name "
			+ " from stu_attendance sa "
			+ "left join student t on t.id=sa.teach_id "
			+ "left join student s on s.id=sa.stu_id "
			+ "left join course c on c.id=sa.course_id "
			+ "left join major m on m.id=s.major_id "
			+ "where 1=1"
			+"<if test=\"stu_realname != null and stu_realname !='' \"> and s.realname like concat('%',#{stu_realname},'%')  </if>"
			+"<if test=\"stu_no != null and stu_no !='' \"> and s.stu_no like concat('%',#{stu_no},'%')  </if>"
			+"<if test=\"date_ != null and date_ !='' \"> and sa.date_ like concat('%',#{date_},'%')  </if>"
			+"<if test=\"status_ != null and status_ !='' \"> and sa.status_ = #{status_}   </if>"
			+"<if test=\"is_face != null and is_face !='' \"> and s.is_face = #{is_face}   </if>"
			+"<if test=\"att_id != null   \"> and  sa.att_id = #{att_id} </if>"
			+"<if test=\"stu_id != null   \"> and  sa.stu_id = #{stu_id} </if>"
			+"<if test=\"orderby == 'cts_desc'   \"> order by sa.cts desc </if>"
			+"</script>")
	List<StuAttendanceInfo> list(StuAttendanceInfo o);


	@Delete("delete from stu_attendance where id = #{0}")
	void delete(String id);

	@Delete("delete from stu_attendance where att_id = #{0}")
	void deleteByAttId(Integer att_id);

}
