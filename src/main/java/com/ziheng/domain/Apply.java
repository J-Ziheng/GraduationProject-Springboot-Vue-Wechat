package com.ziheng.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 学生请假申请信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Apply {
    private Integer id;
    private Integer stuid;
    private String apply_content;
    private String  apply_time;
    private Integer teachid;
    private Integer course_attendanceid;
    private Integer stu_att_id;//学生考勤id
    private Integer status;
    private String result_time;
    private String stu_name;
    private String stu_no;
    private String teach_name;
    private String stu_major;

    //以下字段不保存数据库
    private String att_stime;//考勤开始时间
    private String att_etime;//考勤结束时间
    private String course_name;//课程名称
    private String date_;
    private String room;




    public String getStu_major() {
        return stu_major;
    }

    public void setStu_major(String stu_major) {
        this.stu_major = stu_major;
    }

    public String getDate_() {
        return date_;
    }

    public void setDate_(String date_) {
        this.date_ = date_;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_no() {
        return stu_no;
    }

    public void setStu_no(String stu_no) {
        this.stu_no = stu_no;
    }

    public String getApply_content() {
        return apply_content;
    }

    public void setApply_content(String apply_content) {
        this.apply_content = apply_content;
    }

    public String getApply_time() {
        return apply_time;
    }

    public void setApply_time(String apply_time) {
        this.apply_time = apply_time;
    }

    public Integer getTeachid() {
        return teachid;
    }

    public void setTeachid(Integer teachid) {
        this.teachid = teachid;
    }

    public String getTeach_name() {
        return teach_name;
    }

    public void setTeach_name(String teach_name) {
        this.teach_name = teach_name;
    }

    public Integer getCourse_attendanceid() {
        return course_attendanceid;
    }

    public void setCourse_attendanceid(Integer course_attendanceid) {
        this.course_attendanceid = course_attendanceid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getResult_time() {
        return result_time;
    }

    public void setResult_time(String result_time) {
        this.result_time = result_time;
    }


    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getAtt_stime() {
        return att_stime;
    }

    public void setAtt_stime(String att_stime) {
        this.att_stime = att_stime;
    }

    public String getAtt_etime() {
        return att_etime;
    }

    public void setAtt_etime(String att_etime) {
        this.att_etime = att_etime;
    }

    public Integer getStu_att_id() {
        return stu_att_id;
    }

    public void setStu_att_id(Integer stu_att_id) {
        this.stu_att_id = stu_att_id;
    }
}
