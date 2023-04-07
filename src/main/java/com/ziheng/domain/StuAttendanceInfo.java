package com.ziheng.domain;

/**
 * 学生课程考勤记录
 */
public class StuAttendanceInfo {
	
  
	private Integer id;
	private Integer att_id;
	private Integer stu_id;
	private Integer course_id;
	private Integer teach_id;
	private String date_; //考勤日期
	private String start_ts;//签到时间
	private String cts;
	private String status_;//0待打卡1正常打卡2迟到打卡3请假4缺勤
	private String type;
	private String is_late;//'late'迟到

	// 可能没用
	private String apply_state; //请假状态（0待审核,1已同意，-1已拒绝）

	private String course_start_ts; 
	private String course_end_ts;
	private String location;
	private String lat;
	private String lng;
	private String room;
	private Integer dk_range;
	
	
	 /*--*/
	private String course_name; //
	private String teach_realname; //
	private String stu_realname;
	private String stu_no;
	private String photo;
	private String orderby;
	private  byte[] face_data;
	private String is_face; //0没有采集人脸1采集人脸


	private String major_name;


	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getCourse_start_ts() {
		return course_start_ts;
	}
	public void setCourse_start_ts(String course_start_ts) {
		this.course_start_ts = course_start_ts;
	}
	public String getCourse_end_ts() {
		return course_end_ts;
	}
	public void setCourse_end_ts(String course_end_ts) {
		this.course_end_ts = course_end_ts;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAtt_id() {
		return att_id;
	}
	public void setAtt_id(Integer att_id) {
		this.att_id = att_id;
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
	public Integer getTeach_id() {
		return teach_id;
	}
	public void setTeach_id(Integer teach_id) {
		this.teach_id = teach_id;
	}
	public String getDate_() {
		return date_;
	}
	public void setDate_(String date_) {
		this.date_ = date_;
	}
	public String getStart_ts() {
		return start_ts;
	}
	public void setStart_ts(String start_ts) {
		this.start_ts = start_ts;
	}
	public String getCts() {
		return cts;
	}
	public void setCts(String cts) {
		this.cts = cts;
	}
	public String getStatus_() {
		return status_;
	}
	public void setStatus_(String status_) {
		this.status_ = status_;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIs_late() {
		return is_late;
	}
	public void setIs_late(String is_late) {
		this.is_late = is_late;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getTeach_realname() {
		return teach_realname;
	}
	public void setTeach_realname(String teach_realname) {
		this.teach_realname = teach_realname;
	}
	public String getStu_realname() {
		return stu_realname;
	}
	public void setStu_realname(String stu_realname) {
		this.stu_realname = stu_realname;
	}
	public String getStu_no() {
		return stu_no;
	}
	public void setStu_no(String stu_no) {
		this.stu_no = stu_no;
	}

	public String getIs_face() {
		return is_face;
	}

	public void setIs_face(String is_face) {
		this.is_face = is_face;
	}

	public byte[] getFace_data() {
		return face_data;
	}

	public void setFace_data(byte[] face_data) {
		this.face_data = face_data;
	}

	public String getApply_state() {
		return apply_state;
	}

	public void setApply_state(String apply_state) {
		this.apply_state = apply_state;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public Integer getDk_range() {
		return dk_range;
	}

	public void setDk_range(Integer dk_range) {
		this.dk_range = dk_range;
	}
}
