package com.ziheng.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ziheng.utils.pageHelper;
import com.ziheng.controller.result.Result;
import com.ziheng.domain.Apply;
import com.ziheng.domain.StuAttendanceInfo;
import com.ziheng.service.ApplyService;
import com.ziheng.service.CourseAttendanceInfoService;
import com.ziheng.service.StuAttendanceInfoService;
import com.ziheng.utils.AutoEndCourseUtil;
import com.sun.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 小程序端 学生 请假申请、 教师通过、驳回
 */
@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    private ApplyService applyService;
    @Autowired
    private StuAttendanceInfoService stuAttendanceInfoService;
    @Autowired
    private CourseAttendanceInfoService courseAttendanceInfoService;


    @RequestMapping("/getList")
    public Result getList() {
        Result result = new Result();

        List<Apply> list = applyService.list(new Apply());
        result.setStatus(1);
        result.setObj(list);
        return result;
    }

    @RequestMapping("/save")
    public Result save(Apply s) {

        Result result = new Result();

        if (s.getId() == null) {
            //设置学生课程考勤里的apply_state
            StuAttendanceInfo byId = stuAttendanceInfoService.getById(s.getStu_att_id() + "");
            byId.setApply_state("0");//设置是待审核
            stuAttendanceInfoService.update(byId);
            s.setApply_time(DateUtils.getNowDateTimeString());//设置申请时间
            s.setTeachid(byId.getTeach_id());
            s.setTeach_name(byId.getTeach_realname());
            s.setStu_no(byId.getStu_no());
            s.setStu_name(byId.getStu_realname());
            s.setStu_major(byId.getMajor_name());
            s.setStatus(0);
            applyService.insert(s);
            result.setStatus(1);
            result.setMsg("添加成功");

        } else {
            s.setResult_time(DateUtils.getNowDateTimeString());//设置审核时间
            //设置学生课程考勤里的apply_state
            StuAttendanceInfo byId = stuAttendanceInfoService.getById(s.getStu_att_id() + "");
            byId.setApply_state(s.getStatus() + "");//设置状态
            if (s.getStatus() == 1) {//如果是同意请假的话
                byId.setStatus_("3");//设置为请假状态

            }
            stuAttendanceInfoService.update(byId);
            //统计这门课的考勤
            if (byId.getStatus_().equals("3"))
                AutoEndCourseUtil.countAtt(stuAttendanceInfoService, courseAttendanceInfoService, byId.getAtt_id());

            applyService.update(s);
            result.setStatus(1);
            result.setMsg("操作成功");
        }

        return result;
    }


    @RequestMapping("/page")
    public Result page(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo
            , Apply s) {
        Result result = new Result();
        PageHelper.startPage(pageNo, pageHelper.pageSize, " id desc ");
        List<Apply> li = applyService.list(s);
        PageInfo<Apply> pageInfo = new PageInfo<Apply>(li, pageHelper.pageSize);
        result.setObj(pageInfo);
        return result;
    }


    @RequestMapping("/del")
    public Result del(Apply s) {
        Result result = new Result();
        try {
            applyService.delete(s.getId());
            result.setStatus(1);
            result.setMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(0);
            result.setMsg("删除失败");
        }

        return result;

    }


}
