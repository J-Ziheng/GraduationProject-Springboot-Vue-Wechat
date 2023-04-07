package com.ziheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ziheng.domain.Major;

/**
 * 专业
 */
@Mapper
public interface MajorMapper {
	
	
	@Select("select * from major where id = #{0}")
    Major getById(String  id);
	
	@Insert("insert into major (major_name,system_id) values (#{major_name},#{system_id}) ")
	void insert(Major o);
	
	
	@Update("<script>update major "
			+"<trim prefix=\"set\" suffixOverrides=\",\">"
			+"<if test=\"major_name != null and major_name != ''\">major_name = #{major_name},</if>"
			+"<if test=\"system_id != null and system_id != ''\">system_id = #{system_id},</if>"
			+"</trim> where id=#{id}</script>")
    void update(Major o);
	
	
	@Select("<script>select m.* ,s.sys_name  from major m left join academy s on  s.id=m.system_id where 1=1"
			+"<if test=\"major_name != null and major_name !='' \"> and  major_name  like concat('%',#{major_name},'%') </if>"
			+"<if test=\"system_id != null and system_id !='' \"> and  system_id  like concat('%',#{system_id},'%') </if>"
			+"</script>")
    List<Major> list(Major o);
	
	
	@Delete("delete from major where id = #{0}")
	void delete(String id);
	 
}