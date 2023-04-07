package com.ziheng.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.ziheng.controller.result.Result;
import com.ziheng.domain.Course;
import com.ziheng.domain.Teacher;
import com.ziheng.service.*;
import com.ziheng.utils.AutoEndCourseUtil;
import com.ziheng.utils.face.CompareFace;
import com.ziheng.utils.face.FaceData;
import com.ziheng.utils.face.FaceUtils;
import com.ziheng.utils.face.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ziheng.utils.pageHelper;
import com.ziheng.domain.StuCourse;
import com.ziheng.domain.StuAttendanceInfo;
import com.sun.DateUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小程序端 学生课程考勤信息 数据交互
 */
@RestController
@RequestMapping("/stuAttendance")
public class StuAttendanceInfoController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StuAttendanceInfoService stuAttendanceInfoService;
    @Autowired
    private CourseAttendanceInfoService courseAttendanceInfoService;

    @Autowired
    private StuCourseService stuCourseService;
    @Autowired
    private StudentService studentService;

    /**
     * 小程序端 课程考勤页面 ，查询和分页
     * 学生登陆的小程序 ，页面就学生的page，得到的就是学生的课程信息
     * @param pageNo
     * @param s
     * @param model
     * @return
     */
    @RequestMapping("/page")
    public Result page(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo
            , StuAttendanceInfo s, Model model) {
        // 因为课程考勤信息发布在首页，因此，这里获取page分块和信息的时候，需要调用自动缺勤方法，让超时打卡的学生缺勤
        // 调用自动缺勤，改变学生status
        AutoEndCourseUtil.changeStatus(stuAttendanceInfoService, courseAttendanceInfoService);

        Result result = new Result();

        // 根据id倒叙排序
        PageHelper.startPage(pageNo, pageHelper.pageSize, " id desc ");


        // 获取学生的课程考勤信息集合
        // TODO 子恒：经测试，li集合这里存在bug，没有封装进teanch_realname ，"teach_realname": null,教师姓名没有被封装进来，小程序端学生登录后首页课程考勤不能显示教师姓名
        List<StuAttendanceInfo> li = stuAttendanceInfoService.list(s);
        // 解决：底层添加内联表查询：select realname from teacher,stu_attendance where stu_attendance.teach_id = teacher.id;
        List<Teacher> teacherList = teacherService.selectTeachername();
        // 依旧是教师姓名不能获取，和web端课程管理不能获取教师姓名bug一致，这里通过相同的办法，将教师姓名注入进集合中
        // 获取教师姓名(底层和courseController的bug一致，sql service impl用的方法都一致)
        // 因为这里是小程序首页，学生的课程考勤展示页面，学生的课程考勤信息关联课程
        for (int i = 0; i <  teacherList.size(); i++) {
            // 从集合中一次拿取一个教师对象
            Teacher teacher = teacherList.get(i);
            // 一次获取一个教师姓名
            String tname = teacher.getRealname();

            StuAttendanceInfo stuAttendanceInfo = li.get(i);
            // 课程考勤信息对象的teacher_realname一次赋值一个
            stuAttendanceInfo.setTeach_realname(tname);
            // 以上，依次赋值
        }



        // 全部考勤信息与pageSize封装进pageInfo
        PageInfo<StuAttendanceInfo> pageInfo = new PageInfo<StuAttendanceInfo>(li, pageHelper.pageSize);
        // setObj
        result.setObj(pageInfo);

        // 学生课程对象
        StuCourse sc = new StuCourse();
        sc.setStu_id(s.getStu_id());
        List<StuCourse> cli = stuCourseService.list(sc);
        result.setObj2(cli);
        return result;
    }

    /**
     * 小程序端 学生考勤信息
     *
     * @param s
     * @return
     */
    @RequestMapping("/page_a")
    public Result page_a(StuAttendanceInfo s) {
        Result result = new Result();

        if (s.getAtt_id() != null) {
            List<StuAttendanceInfo> li = stuAttendanceInfoService.list(s);
            result.setStatus(1);
            result.setObj(li);
        }
        return result;
    }

    /**
     * 根据id查询学生的考勤信息
     *
     * @param pageNo
     * @param s      学生考勤信息
     * @return
     */
    @RequestMapping("/info")
    public Result info(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, StuAttendanceInfo s) {
        Result result = new Result();
        if (s.getId() != null) {
            s = stuAttendanceInfoService.getById(s.getId() + "");
            // 返回1 开始签到
            result.setStatus(1);
            result.setObj(s);
            result.setMsg("请求成功");
        }
        return result;
    }

    /**
     * 判断学生当前时间信息，是否符合签到条件：上课日期与课程时间。
     * 符合条件返回 1 ，其余返回 0
     *
     * @param pageNo
     * @param s
     * @return
     */
    @RequestMapping("/canReport")
    public Result canReport(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, StuAttendanceInfo s) {
        Result result = new Result();
        if (s.getId() != null) {
            s = stuAttendanceInfoService.getById(s.getId() + "");
            String today = DateUtils.getNowDateString();
            String hm = DateUtils.getNowHMString();

            if (!s.getDate_().equals(today)) {
                result.setStatus(0);
                result.setMsg("上课日期错误");
                return result;
            }
            if (s.getCourse_end_ts().compareTo(hm) < 0 || s.getCourse_end_ts().compareTo(hm) == 0) {
                result.setStatus(0);
                result.setMsg("课程已结束");
                return result;
            }

            long nts = new Date().getTime();
            String str = s.getDate_() + " " + s.getCourse_start_ts() + ":00";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = simpleDateFormat.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long sts = date.getTime();

            if (sts - nts > 1000 * 60 * 20) {
                result.setStatus(0);
                result.setMsg("上课时间的前20分钟内才可以签到");
                return result;
            }

            result.setStatus(1);
            result.setMsg("符合签到条件");
            return result;

        }

        result.setStatus(0);
        result.setMsg("该数据不存在");
        return result;
    }

    /**
     * 人脸识别--刷脸签到模块--人脸相似度比对
     * 0待打卡 1正常打卡 2迟到打卡 3请假 4缺勤
     *
     * @param
     * @param session--session会话暂未使用--使用util：token
     * @return
     * @throws Exception
     */
    @RequestMapping("/HRfaceadd")
    public Result HRfaceadd(StuAttendanceInfo s, HttpSession session, String base64) throws Exception {
        Result result = new Result();
        // 获取今天日期及现在时间 2023-03-07 14:16:07
        String cts = DateUtils.getNowDateTimeString();
        // 今天日期 2023-03-07
        String today = DateUtils.getNowDateString();
        // 获取现在时间  14:16
        String hm = DateUtils.getNowHMString();

        // 人脸识别前根据学生id获取学生考勤的信息，判断学生的status是1已打卡 2迟到打卡 4缺勤
        // 3 请假 ，请假已经不可申请定位和人脸识别 0/-1 课程结束
        // 根据学生考勤信息表id查询
        s = stuAttendanceInfoService.getById(s.getId() + "");

        //0待打卡1正常打卡2迟到打卡3请假4缺勤
        if (s.getStatus_().equals("1") || s.getStatus_().equals("2")) {
            result.setStatus(-1);
            result.setMsg("已签到，请勿重复签到");
            return result;
        }
        if (s.getStatus_().equals("4")) {
            result.setStatus(-1);
            result.setMsg("已旷课，不可签到");
            return result;
        }

        if (!s.getDate_().equals(today)) {
            result.setStatus(-1);
            result.setMsg("上课日期错误");
            return result;
        }

        if (s.getCourse_end_ts().compareTo(hm) < 0 || s.getCourse_end_ts().compareTo(hm) == 0) {
            result.setStatus(-1);
            result.setMsg("课程已结束");
            return result;
        }

        /*
         * 开始计算时间 此刻打卡时间，转为年月日时分秒标准格式
         */
        long nts = new Date().getTime();
        // 1680848504875 获取当前时间 单位 s（还记得时间吗，计算机的时间起点是六万多秒还是多少来着，在学习日期类的时候，时间以此为起点计算）
        String str = s.getDate_() + " " + s.getCourse_start_ts() + ":00";
        // 转换时间格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 此刻时间转为s值
        long sts = date.getTime();
        // 1680848504875
        // 时间计算，计算当前时间与签到开始时间 秒数 差，
        // 如果上课开始时间秒数 减去 当前时间秒数 大于  20分*60秒  就说明来早了，虽然签到已发布，但还没到签到时间(课前20min)
        // 上课开始时间：15:00  我的点击签到时间：14：30   二者秒数之差为30min*60s 大于了 20min*60s，发出提示，课前二十分钟才可以签到
        // 忘记计算机的秒数起始时间，就去复习Date工具类的使用，笔记首先有计算机的时间起点 单位：s，以此为基，计算当前、过去、未来时间
        if (sts - nts > 1000 * 60 * 20) {
            result.setStatus(-1);
            result.setMsg("上课时间的前20分钟内才可以签到");
            return result;
        }

        // 计算迟到时间
        String late = "";
        // hm：此刻时间，如果此刻时间 大于 从教师发布的考勤信息表 查询到的开始时间 则视为迟到，可打卡，但迟到
        if (hm.compareTo(s.getCourse_start_ts()) > 0) {
            late = "late";
        }
        byte[] bytes = ImageUtils.base64ToByte(base64);//base64位图转成byte[]
        FaceData faceData = null;
        try {
            // FaceUtils虹软人脸识别接口 获取人脸数据：字节
            faceData = FaceUtils.getFaceData(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            // 返回前端：status 0 ，未检测到人脸-请正对摄像头重新识别
            result.setStatus(0);
            result.setMsg("未检测到人脸-请正对摄像头重新识别");
            return result;
        }

        //判断是否检测到人脸
        // 如果人脸数据为空 并且 已经采集过人脸（validateFace人脸特征值状态码（判断是否采集成功））
        if (faceData == null || faceData.getValidateFace() != 0) {
            result.setStatus(0);
            result.setMsg("检测人脸失败-请正对摄像头重新识别");
            return result;
        } else if (faceData.getValidatePoint() != 0) {
            result.setStatus(0);
            result.setMsg("获取人脸特征值失败-请重新采集");
            return result;

        }
        // getIs_face 0没有采集人脸1采集人脸
        // 已经采集人脸  Student数据库表的is_face存储为1   byte不为null值
        if (s.getIs_face() != null && s.getIs_face().equals("1") && s.getFace_data() != null) {
            // 满足上述条件，可开始打卡
            CompareFace compare = FaceUtils.compare(faceData.getFaceData(), s.getFace_data());
            // 如果对比人脸相似度大于0.8 则可打卡：正常打卡或迟到
            if (compare.getScore() >= 0.8) {
                s.setCts(cts);
                if (!late.equals("late")) {
                    s.setStatus_("1");//正常打卡
                } else {
                    s.setStatus_("2");//迟到打卡
                }
                s.setIs_late(late);
                s.setType("face");
                s.setStart_ts(hm);
                stuAttendanceInfoService.update(s);
                /*添加该门课的考勤数据*/
                AutoEndCourseUtil.countAtt(stuAttendanceInfoService, courseAttendanceInfoService, s.getAtt_id());

                result.setStatus(1);
                result.setMsg("签到成功");
                return result;

            } else {
                result.setStatus(0);
                result.setMsg("不是本人");
                return result;
            }
        } else {
            result.setStatus(-1);
            result.setMsg("数据库未采集您的人脸信息，请联系管理员");
            return result;
        }


    }

    /**
     * 统计某门课的学生总考勤
     *
     * @param s
     * @return
     */
    @RequestMapping("/stuAtt")
    public Result stuAtt(StuAttendanceInfo s) {
        Result result = new Result();

        StuCourse c = new StuCourse();
        c.setCourse_id(s.getCourse_id());
        List<StuCourse> sli = stuCourseService.list(c);

        for (StuCourse cs : sli) {
            StuAttendanceInfo sc = new StuAttendanceInfo();
            sc.setStu_id(cs.getStu_id());
            sc.setCourse_id(s.getCourse_id());

            sc.setStatus_("1");
            int normal_come_ci = stuAttendanceInfoService.getByCount(sc);
            cs.setNormal_come_ci(normal_come_ci);

            sc.setStatus_("2");
            int late_ci = stuAttendanceInfoService.getByCount(sc);
            cs.setLate_ci(late_ci);

            sc.setStatus_("3");
            int qj_ci = stuAttendanceInfoService.getByCount(sc);
            cs.setQj_ci(qj_ci);

            sc.setStatus_("4");
            int not_come = stuAttendanceInfoService.getByCount(sc);
            cs.setNot_come(not_come);
        }
        result.setStatus(1);
        result.setObj(sli);
        return result;
    }

    /**
     * 查询学生考勤的具体信息，比如某门课的学生那几次上课迟到
     *
     * @param s
     * @return
     */
    @RequestMapping("/stuAttInfo")
    public Result stuAttInfo(StuAttendanceInfo s) {
        Result result = new Result();
        List<StuAttendanceInfo> li = stuAttendanceInfoService.list(s);
        result.setStatus(1);
        result.setObj(li);
        return result;
    }
}
