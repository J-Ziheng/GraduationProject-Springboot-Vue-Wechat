package com.ziheng.domain;

/**
 * 课程考勤 信息
 */
public class CourseAttendanceInfo {
	private Integer id;
	private String date_; //考勤日期
	private String start_ts; //开始时间
	private String end_ts; //结束时间
	private Integer course_id;
	private String has_attendance;// 考勤发布状态 1已发布 0未发布
	private String cts; //本次考勤创建时间
	private Integer come_num; //签到人数
	private Integer all_num; //总人数
	private Integer not_come_num;//旷课人数
	private Integer teach_id;
	private String location;
	private String lat;
	private String lng;
	private String room;
	private Integer dk_range;
	private Integer qj_num;//请假人数
	private Integer late_num;//迟到人数
	private Integer normal_come_num;//正常签到人数
	private Integer wait_come_num;//待签到人数

	/*不存在*/
	private String course_name;
	private String teach_realname;
	private String major_name;

	public Integer getWait_come_num() {
		return wait_come_num;
	}

	public void setWait_come_num(Integer wait_come_num) {
		this.wait_come_num = wait_come_num;
	}

	public Integer getQj_num() {
		return qj_num;
	}

	public void setQj_num(Integer qj_num) {
		this.qj_num = qj_num;
	}

	public Integer getLate_num() {
		return late_num;
	}

	public void setLate_num(Integer late_num) {
		this.late_num = late_num;
	}

	public Integer getNormal_come_num() {
		return normal_come_num;
	}

	public void setNormal_come_num(Integer normal_come_num) {
		this.normal_come_num = normal_come_num;
	}

	public String getMajor_name() {
		return major_name;
	}

	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}

	public Integer getTeach_id() {
		return teach_id;
	}
	public void setTeach_id(Integer teach_id) {
		this.teach_id = teach_id;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getEnd_ts() {
		return end_ts;
	}
	public void setEnd_ts(String end_ts) {
		this.end_ts = end_ts;
	}
	public String getHas_attendance() {
		return has_attendance;
	}
	public void setHas_attendance(String has_attendance) {
		this.has_attendance = has_attendance;
	}
	public String getCts() {
		return cts;
	}
	public void setCts(String cts) {
		this.cts = cts;
	}

	public Integer getCome_num() {
		return come_num;
	}
	public void setCome_num(Integer come_num) {
		this.come_num = come_num;
	}
	public Integer getAll_num() {
		return all_num;
	}
	public void setAll_num(Integer all_num) {
		this.all_num = all_num;
	}
	public Integer getNot_come_num() {
		return not_come_num;
	}
	public void setNot_come_num(Integer not_come_num) {
		this.not_come_num = not_come_num;
	}

	public Integer getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
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

	public Integer getDk_range() {
		return dk_range;
	}

	public void setDk_range(Integer dk_range) {
		this.dk_range = dk_range;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
}
