package com.ziheng.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.ziheng.domain.Admin;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理员
 */
@Mapper
public interface AdminMapper {
	
	@Select("select * from admin where uname = #{uname} and upass=#{upass}")
    Admin login(Admin admin);

	@Select("select * from admin where id=#{id}")
    Admin getById(String id);
	
	@Select("update admin set  upass=#{upass}  where id=#{id}")
    void update(Admin admin);

    /**
     * uname = #{uname} 这种方式mybatis不能插入数据库值，不知道为什么，先前学习springBoot就可以，可能是mybatis版本问题？
     * @param admin
     */
    @Insert("insert into admin (id, uname, upass) values (#{id},#{uname},#{upass}) ")
    void insert(Admin admin);

	 
}