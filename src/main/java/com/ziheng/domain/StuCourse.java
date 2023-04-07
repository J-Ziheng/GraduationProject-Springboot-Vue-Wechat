package com.ziheng.domain;

/**
 * 学生课程对应表
 * 学生的选课课程
 */
public class StuCourse {
	private Integer id;


	private Integer stu_id;
	private Integer course_id;



	/*--*/
	private String stu_no;
	private String realname;
	private String sex;
	private String major_name;
	private String sys_name;
	private Integer  major_id;
	private String  sys_id ;
	private String  course_name ;

	private Integer normal_come_ci;//正常打卡次数
	private Integer late_ci;//迟到次数
	private Integer qj_ci;//请假次数
	private Integer not_come;//旷课次数


	public Integer getNormal_come_ci() {
		return normal_come_ci;
	}

	public void setNormal_come_ci(Integer normal_come_ci) {
		this.normal_come_ci = normal_come_ci;
	}

	public Integer getLate_ci() {
		return late_ci;
	}

	public void setLate_ci(Integer late_ci) {
		this.late_ci = late_ci;
	}

	public Integer getQj_ci() {
		return qj_ci;
	}

	public void setQj_ci(Integer qj_ci) {
		this.qj_ci = qj_ci;
	}

	public Integer getNot_come() {
		return not_come;
	}

	public void setNot_come(Integer not_come) {
		this.not_come = not_come;
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

	public Integer getStu_id() {
		return stu_id;
	}

	public void setStu_id(Integer stu_id) {
		this.stu_id = stu_id;
	}

	public Integer getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}

	public String getStu_no() {
		return stu_no;
	}
	public void setStu_no(String stu_no) {
		this.stu_no = stu_no;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMajor_name() {
		return major_name;
	}
	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}
	public String getSys_name() {
		return sys_name;
	}
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}
	public String getSys_id() {
		return sys_id;
	}
	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}

	public Integer getMajor_id() {
		return major_id;
	}

	public void setMajor_id(Integer major_id) {
		this.major_id = major_id;
	}
}
