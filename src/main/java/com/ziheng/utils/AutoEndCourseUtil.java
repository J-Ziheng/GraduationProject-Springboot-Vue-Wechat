package com.ziheng.utils;

import com.ziheng.domain.CourseAttendanceInfo;
import com.ziheng.domain.StuAttendanceInfo;
import com.ziheng.service.CourseAttendanceInfoService;
import com.ziheng.service.StuAttendanceInfoService;
import com.sun.DateUtils;

import java.util.List;


/**
 *
 *  @author ziheng.com
 *
 */
public class AutoEndCourseUtil {


    //自动缺勤
     public static void changeStatus(StuAttendanceInfoService stuAttendanceInfoService, CourseAttendanceInfoService courseAttendanceInfoService) {

         String day = DateUtils.getNowDateString();
         String hm = DateUtils.getNowHMString();
         //查出需要自动旷课的学生考勤
         List<StuAttendanceInfo> li= stuAttendanceInfoService.getAbsenteeism(day,hm);

         //自动设置旷课
         stuAttendanceInfoService.updateAbsenteeism(day,hm);

         //修改状态后对以上数据开始统计
         for(StuAttendanceInfo s:li){
             countAtt(stuAttendanceInfoService, courseAttendanceInfoService, s.getAtt_id() );
         }
     }
     //统计某节课的考勤情况
     public static void countAtt(StuAttendanceInfoService stuAttendanceInfoService, CourseAttendanceInfoService courseAttendanceInfoService, Integer course_att_id){
         StuAttendanceInfo s1 = new StuAttendanceInfo();
         s1.setAtt_id(course_att_id);

        // 待打卡
         s1.setStatus_("0");
         Integer wait_come_num = stuAttendanceInfoService.getByCount(s1);

        // 正常打卡
         s1.setStatus_("1");
         Integer normal_come_num = stuAttendanceInfoService.getByCount(s1);
        // 迟到
         s1.setStatus_("2");
         Integer late_num =  stuAttendanceInfoService.getByCount(s1);

        // 请假
         s1.setStatus_("3");
         Integer qj_num = stuAttendanceInfoService.getByCount(s1);

        // 缺勤
         s1.setStatus_("4");
         Integer not_come_num = stuAttendanceInfoService.getByCount(s1);

         Integer come_num=normal_come_num+late_num;
         CourseAttendanceInfo o=new CourseAttendanceInfo();
         o.setId(course_att_id);
         o.setCome_num(come_num==null?0:come_num);
         o.setNot_come_num(not_come_num==null?0:not_come_num);
         o.setNormal_come_num(normal_come_num==null?0:normal_come_num);
         o.setQj_num(qj_num==null?0:qj_num);
         o.setWait_come_num(wait_come_num==null?0:wait_come_num);
         o.setLate_num(late_num==null?0:late_num);
         courseAttendanceInfoService.update(o);
     }


}

