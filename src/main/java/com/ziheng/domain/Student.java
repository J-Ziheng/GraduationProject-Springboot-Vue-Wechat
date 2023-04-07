package com.ziheng.domain;

/**
 *  学生
 */
public class Student {
	private Integer id;
 
	private String upass; 
	private String realname; 
	private String stu_no; 
	private String sex;
	private Integer sys_id;//所属院系id
	private Integer major_id;//所属专业id
	private String photo;//电子照片
	private String token;
	private  byte[] face_data;
	private String is_face; //0没有采集人脸1采集人脸
	
	//以下的属性是为了传值
	private String sys_name;//院系名字
	private String major_name;//专业名字


	public byte[] getFace_data() {
		return face_data;
	}

	public void setFace_data(byte[] face_data) {
		this.face_data = face_data;
	}

	public String getIs_face() {
		return is_face;
	}

	public void setIs_face(String is_face) {
		this.is_face = is_face;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getSys_id() {
		return sys_id;
	}

	public void setSys_id(Integer sys_id) {
		this.sys_id = sys_id;
	}

	public Integer getMajor_id() {
		return major_id;
	}

	public void setMajor_id(Integer major_id) {
		this.major_id = major_id;
	}

	public String getSys_name() {
		return sys_name;
	}
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}
	public String getMajor_name() {
		return major_name;
	}
	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}
	
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
	public String getStu_no() {
		return stu_no;
	}
	public void setStu_no(String stu_no) {
		this.stu_no = stu_no;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	
	 
	 
	
}
