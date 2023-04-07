package com.ziheng.domain;

import lombok.ToString;

/**
 * 教师
 */

public class Teacher {
	private Integer id;
 
	private String upass; 
	private String realname; 
	private String teach_no; 
	private String sex;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	 
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	 
	public String getTeach_no() {
		return teach_no;
	}
	public void setTeach_no(String teach_no) {
		this.teach_no = teach_no;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	} 
	 
	 
	 
	 
	
}
