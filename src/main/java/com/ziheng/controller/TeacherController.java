package com.ziheng.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ziheng.utils.pageHelper;
import com.ziheng.controller.result.Result;
import com.ziheng.domain.Teacher;
import com.ziheng.service.StuAttendanceInfoService;
import com.ziheng.service.TeacherService;
import com.sun.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 教师管理
 * Web管理端&小程序端登录、修改密码
 */
@RestController
@RequestMapping("/teach")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StuAttendanceInfoService stuAttendanceInfoService;

    /**
     * Web管理端 查询所有教师
     *
     * @return
     */
    @RequestMapping("/getList")
    public Result getList() {
        Result result = new Result();

        List<Teacher> list = teacherService.list(new Teacher());
//        List<Teacher> list = teacherService.list();
        result.setStatus(1);
        result.setObj(list);
        return result;
    }

    /**
     * Web管理端 教师工号与数据库查询比对 判断是否存在|重复
     *
     * @param t
     * @return
     */
    @RequestMapping("/check_teach_no")
    public Result check_teach_no(Teacher t) {
        Result result = new Result();
        if (t.getId() != null) {
            Teacher byId = teacherService.getById(t.getId() + "");
            if (byId.getTeach_no().equals(t.getTeach_no())) {
                result.setStatus(1);
                result.setMsg("该编号可用");
                return result;
            }
        }

        t = teacherService.getByTeachNo(t.getTeach_no());
        if (t != null) {
            result.setStatus(0);
            result.setMsg("该编号已使用，请换一个");
        } else {
            result.setStatus(1);
            result.setMsg("该编号可用");
        }
        return result;
    }

    /**
     * Web管理端 添加或修改教师信息
     *
     * @param pageNo
     * @param t
     * @param model
     * @return
     */
    @RequestMapping("/save")
    public Result save(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, Teacher t, Model model) {
        Result result = new Result();
        if (t.getId() == null) {
            Teacher ct = teacherService.getByTeachNo(t.getTeach_no());
            if (ct != null) {
                result.setStatus(0);
                // 返回数据库查询到的已存在的教师编号
                result.setMsg(t.getTeach_no() + "编号已使用，添加失败");
                return result;
            } else {
                // 设置密码 默认后4位
                t.setUpass(t.getTeach_no().substring(t.getTeach_no().length() - 4, t.getTeach_no().length()));
                teacherService.insert(t);
                result.setStatus(1);
                result.setMsg("添加成功");
                return result;
            }

        } else {
            teacherService.update(t);
            result.setStatus(1);
            result.setMsg("修改成功");
            return result;
        }

    }

    /**
     * Web管理端 教师 分页工具
     *
     * @param pageNo
     * @param t
     * @return
     */
    @RequestMapping("/page")
    public Result page(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, Teacher t) {
        Result result = new Result();
        PageHelper.startPage(pageNo, pageHelper.pageSize, " id desc ");
        List<Teacher> li = teacherService.list(t);
        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(li, pageHelper.pageSize);
        result.setStatus(1);
        result.setObj(pageInfo);
        return result;

    }

    /**
     * Web管理端 删除教师
     *
     * @param pageNo
     * @param t
     * @return
     */
    @RequestMapping("/del")
    public Result del(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, Teacher t) {
        Result result = new Result();
        try {
            teacherService.delete(t.getId() + "");
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
     * Web管理端 初始化密码
     * 默认密码后四位
     * @param pageNo
     * @param t
     * @param model
     * @return
     */
    @RequestMapping("/init_pwd")
    public Result init_pwd(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, Teacher t, Model model) {
        Result result = new Result();
        t = teacherService.getById(t.getId() + "");
        t.setUpass(t.getTeach_no().substring(t.getTeach_no().length() - 4, t.getTeach_no().length()));
        teacherService.update(t);
        result.setStatus(1);
        result.setMsg("密码初始化成功");
        return result;
    }

    /**
     * 小程序端 教师登录
     *
     * @param t
     * @return
     */
    @RequestMapping("/login")
    public Result login(Teacher t) {
        // 今天日期
        String day = DateUtils.getNowDateString();
        // 此刻时间
        String hm = DateUtils.getNowHMString();
        //登陆同时 获取所有考勤日期 供小程序首页课程考勤 进行展示
        stuAttendanceInfoService.updateAbsenteeism(day, hm);

        Result result = new Result();
        if (t.getTeach_no() == null || t.getTeach_no().isEmpty()) {
            result.setStatus(0);
            result.setMsg("请输入教师编号");
            return result;
        }
        if (t.getUpass() == null || t.getUpass().isEmpty()) {
            result.setStatus(0);
            result.setMsg("请输入密码");
            return result;
        }
        List<Teacher> li = teacherService.list(t);
        if (li != null && li.size() > 0) {
            t = li.get(0);
            result.setStatus(1);
            result.setObj(t);
            result.setMsg("登录成功");
            return result;
        } else {
            result.setStatus(0);
            result.setMsg("账号或者密码错误");
            return result;
        }

    }

    /**
     * 小程序端 教师密码修改
     *
     * @param a
     * @param req
     * @param session
     * @return
     */
    @RequestMapping("/updatePwd")
    public Result updatePwd(Teacher a, HttpServletRequest req, HttpSession session) {
        Result result = new Result();
        String old_upass = req.getParameter("oldp");
        String new_upass = req.getParameter("newp");
        String new_upass2 = req.getParameter("newp2");
        System.out.println("这是小程序教师的对象a的id："+a.getId());
        a = teacherService.getById(a.getId() + "");
//        a = teacherService.getById(a.getId());

        if (!a.getUpass().equals(old_upass)) {
            result.setStatus(0);
            result.setMsg("原始密码输入错误");
            return result;
        }
        if (!new_upass.equals(new_upass2)) {
            result.setStatus(0);
            result.setMsg("密码输入不一致！");
            return result;
        }
        a.setUpass(new_upass);
        teacherService.update(a);
        result.setStatus(1);
        result.setMsg("密码修改成功！");
        return result;

    }
}
