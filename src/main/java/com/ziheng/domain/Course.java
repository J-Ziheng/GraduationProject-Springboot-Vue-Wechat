package com.ziheng.domain;

/**
 * 课程
 */
// EtCourse
public class Course {// 课程
	private Integer id;
	private String course_name;
	private Integer major_id;
	private Integer teach_id;
	private String has_stu;

	private String has_theme;// 未知

	// 以下属性是为了方便传值，不需要保存到数据库
	private String major_name;//专业名称
	private String realname;//老师名字

	public String getHas_theme() {
		return has_theme;
	}

	public void setHas_theme(String has_theme) {
		this.has_theme = has_theme;
	}

	public Integer getMajor_id() {
		return major_id;
	}

	public void setMajor_id(Integer major_id) {
		this.major_id = major_id;
	}

	public Integer getTeach_id() {
		return teach_id;
	}

	public void setTeach_id(Integer teach_id) {
		this.teach_id = teach_id;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}


	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHas_stu() {
		return has_stu;
	}

	public void setHas_stu(String has_stu) {
		this.has_stu = has_stu;
	}


	

}
