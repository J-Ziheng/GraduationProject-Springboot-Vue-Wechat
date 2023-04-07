package com.ziheng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.ziheng.domain.Academy;

/**
 * 学院
 */
@Mapper
public interface AcademyMapper {
	
	@Select("select * from academy where id = #{0}")
	Academy getById(String  id);
	
	@Insert("insert into academy (sys_name) values (#{sys_name}) ")
	void insert(Academy o);
	
	
	@Update("<script>update academy"
			+"<trim prefix=\"set\" suffixOverrides=\",\">"
			+"<if test=\"sys_name != null and sys_name != ''\">sys_name = #{sys_name},</if>"
			+"</trim> where id=#{id}</script>")
    void update(Academy o);
	
	
	@Select("<script>select * from academy where 1=1"
			+"<if test=\"sys_name != null and sys_name !='' \"> and  sys_name  like concat('%',#{sys_name},'%') </if>"
			+"</script>")
    List<Academy> list(Academy o);
	
	
	@Delete("delete from academy where id = #{0}")
	void delete(String id);
	 
}