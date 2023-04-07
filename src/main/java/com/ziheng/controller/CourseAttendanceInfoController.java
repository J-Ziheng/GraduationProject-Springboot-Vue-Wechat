package com.ziheng.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ziheng.utils.pageHelper;
import com.ziheng.controller.result.Result;
import com.ziheng.domain.CourseAttendanceInfo;
import com.ziheng.domain.StuCourse;
import com.ziheng.domain.StuAttendanceInfo;
import com.ziheng.service.CourseAttendanceInfoService;
import com.ziheng.service.StuCourseService;
import com.ziheng.service.StuAttendanceInfoService;
import com.ziheng.utils.AutoEndCourseUtil;
import com.sun.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 小程序端 课程考勤信息发布 教师发布考勤
 */
@RestController
@RequestMapping("/courseAttendance")
public class CourseAttendanceInfoController {
    @Autowired
    private StuCourseService stuCourseService;
    @Autowired
    private StuAttendanceInfoService stuAttendanceInfoService;
    @Autowired
    private CourseAttendanceInfoService courseAttendanceInfoService;


    /**
     * 课程考勤信息保存模块
     *
     * @param t
     * @return
     */
    @RequestMapping("/save")
    public Result save(CourseAttendanceInfo t) {
        Result result = new Result();

        String today = DateUtils.getNowDateString();

        if (t.getDate_().compareTo(today) < 0) {
            result.setStatus(0);
            result.setMsg("上课日期错误");
            return result;
        }


        if (t.getDate_().compareTo(today) == 0) {
            if (t.getStart_ts().compareTo(DateUtils.getNowHMString()) < 0) {
                result.setStatus(0);
                result.setMsg("已超时");
                return result;
            }
        }


        if (t.getStart_ts().compareTo(t.getEnd_ts()) >= 0) {
            result.setStatus(0);
            result.setMsg("结束时间错误");
            return result;
        }

        if (t.getId() == null) {
            int n = stuCourseService.getCountByCourseId(t.getCourse_id());
            t.setAll_num(n);//  更新课程总人数
            t.setHas_attendance("0");// 当前考勤状态更新初始值
            t.setWait_come_num(n);
            t.setNormal_come_num(0);
            t.setNot_come_num(0);
            t.setQj_num(0);
            t.setCome_num(0);
            t.setLate_num(0);

            courseAttendanceInfoService.insert(t);
            result.setStatus(1);
            result.setMsg("添加成功");
        } else {
            courseAttendanceInfoService.update(t);
            result.setStatus(1);
            result.setMsg("修改成功");
        }

        return result;

    }

    /**
     * 根据Id获取教师发布的课程考勤信息
     *
     * @param t
     * @return
     */
    @RequestMapping("/info")
    public Result info(CourseAttendanceInfo t) {
        Result result = new Result();

        if (t.getId() != null) {
            t = courseAttendanceInfoService.getById(t.getId());
            result.setObj(t);
            result.setStatus(1);
            result.setMsg("请求成功");
        }
        return result;
    }

    /**
     * 课程考勤信息查找&分页模块
     *
     * @param pageNo
     * @param t
     * @return
     */
    @RequestMapping("/page")
    public Result page(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, CourseAttendanceInfo t) {
        AutoEndCourseUtil.changeStatus(stuAttendanceInfoService, courseAttendanceInfoService);

        Result result = new Result();
        // pageNo传参 默认是1 ，当前为第几页。 pageSize:每页的数据行数
        PageHelper.startPage(pageNo, pageHelper.pageSize, " id desc ");
        List<CourseAttendanceInfo> li = courseAttendanceInfoService.list(t);
        // 分页pageNum：当前为第几页 pageSize：每页的数据行数 li数据总数
        PageInfo<CourseAttendanceInfo> pageInfo = new PageInfo<CourseAttendanceInfo>(li, pageHelper.pageSize);
        result.setObj(pageInfo);
        result.setStatus(1);

        return result;
    }

    /**
     * 删除课程考勤记录模块
     *
     * @param t
     * @return
     */
    @RequestMapping("/del")
    public Result del(CourseAttendanceInfo t) {
        Result result = new Result();
        try {
            courseAttendanceInfoService.delete(t.getId());
            stuAttendanceInfoService.deleteByAttId(t.getId());

            /*添加该门课的考勤数据*/
            AutoEndCourseUtil.countAtt(stuAttendanceInfoService, courseAttendanceInfoService, t.getId());


            result.setStatus(1);
            result.setMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(0);
            result.setMsg("删除失败");
        }
        return result;

    }

    /**
     * 发送考勤命令模块
     *
     * @param t
     * @return
     */
    @RequestMapping("/send")
    public Object send(CourseAttendanceInfo t) {
        Result result = new Result();

        Map m = new HashMap();
        t = courseAttendanceInfoService.getById(t.getId());

        String today = DateUtils.getNowDateString();

        if (t.getDate_().compareTo(today) < 0) {
            result.setStatus(0);
            result.setMsg("上课日期错误");
            return result;
        }


        if (t.getDate_().compareTo(today) == 0) {
            if (t.getStart_ts().compareTo(DateUtils.getNowHMString()) < 0) {
                result.setStatus(0);
                result.setMsg("已超时");
                return result;
            }
        }


        if (t.getStart_ts().compareTo(t.getEnd_ts()) >= 0) {
            result.setStatus(0);
            result.setMsg("结束时间错误");
            return result;
        }


        StuCourse cs = new StuCourse();
        cs.setCourse_id(t.getCourse_id());
        List<StuCourse> sli = stuCourseService.list(cs);

        for (StuCourse ss : sli) {
            StuAttendanceInfo sa = new StuAttendanceInfo();
            sa.setAtt_id(t.getId());
            sa.setCourse_id(t.getCourse_id());
            sa.setTeach_id(t.getTeach_id());
            sa.setStu_id(ss.getStu_id());
            sa.setDate_(t.getDate_());
            sa.setStatus_("0");
            sa.setCourse_start_ts(t.getStart_ts());
            sa.setCourse_end_ts(t.getEnd_ts());
            sa.setLocation(t.getLocation());
            sa.setLat(t.getLat());
            sa.setLng(t.getLng());
            sa.setRoom(t.getRoom());
            sa.setDk_range(t.getDk_range());
            stuAttendanceInfoService.insert(sa);
        }

        t.setHas_attendance("1");
        courseAttendanceInfoService.update(t);

        result.setStatus(1);
        result.setMsg("已发布成功");
        return result;

    }


}












