package com.ziheng.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ziheng.utils.pageHelper;
import com.ziheng.controller.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ziheng.domain.Course;
import com.ziheng.domain.StuCourse;
import com.ziheng.domain.Student;
import com.ziheng.domain.Academy;
import com.ziheng.domain.Teacher;
import com.ziheng.service.CourseService;
import com.ziheng.service.StuCourseService;
import com.ziheng.service.StudentService;
import com.ziheng.service.AcademyService;
import com.ziheng.service.TeacherService;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生课程
 */
@RestController
@RequestMapping("/courseStu")
public class StuCourseController {
    @Autowired
    private StuCourseService stuCourseService;

    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AcademyService academyService;

    /*
     * 给学生分配结构
     * 在控制器中，数据会存放到Model对象中，当需要生成HTML的时候，模板引擎会根据名字来定位数据
     * 广义：Model指的是MVC中的M，即Model模型
     * 狭义：Model就是个key-value集合
     */
    @RequestMapping("/toaddStu")
    public String toaddStu(HttpServletRequest req, Model model) {
        Course c = courseService.getById(req.getParameter("course_id"));
        model.addAttribute("c", c);

        Teacher teach = teacherService.getById(c.getTeach_id() + "");
        model.addAttribute("teach", teach);

        List<Academy> systems = academyService.list(null);
        model.addAttribute("systems", systems);

        return "add_stu_to_course";
    }

    /**
     * 封装学生所有信息返回给web端
     * <p>
     * 在控制器中，数据会存放到Model对象中，当需要生成HTML的时候，模板引擎会根据名字来定位数据
     * 广义：Model指的是MVC中的M，即Model模型
     * 狭义：Model就是个key-value集合
     *
     * @param s
     * @param model
     * @param req
     * @return
     */
    @RequestMapping("/stulist")
    public String stulist(Student s, Model model, HttpServletRequest req) {
        Course c = courseService.getById(req.getParameter("course_id"));
        model.addAttribute("c", c);

        Teacher teach = teacherService.getById(c.getTeach_id() + "");
        model.addAttribute("teach", teach);

        List<Student> stuli = studentService.list(s);
        model.addAttribute("stuil", stuli);
        model.addAttribute("stucount", stuli.size());

        //将现在所有的院系返回给前端
        List<Academy> systems = academyService.list(null);
        model.addAttribute("systems", systems);
        model.addAttribute("s", s);
        return "add_stu_to_course";
    }

    /**
     * 添加课程
     * 课程绑定学生id 所属专业 教师 院系
     *
     * @param s
     * @param model
     * @param req
     * @return
     */
    @RequestMapping("/save")
    public String save(Student s, Model model, HttpServletRequest req) {
        String course_id = req.getParameter("course_id");
        String[] stu_ids = req.getParameterValues("stu_ids");
        for (String stu_id : stu_ids) {
            StuCourse cs = new StuCourse();
            cs.setCourse_id(Integer.parseInt(course_id));
            cs.setStu_id(Integer.parseInt(stu_id));
            List<StuCourse> csli = stuCourseService.list(cs);
            if (!(csli != null && csli.size() > 0)) {
                stuCourseService.insert(cs);
            }
        }
        Course c = courseService.getById(course_id);
        c.setHas_stu("1");
        courseService.update(c);
        return "redirect:/courseStu/page?course_id=" + course_id;
    }

    /**
     * 分页工具
     *
     * @param pageNo
     * @param req
     * @param cs
     * @return
     */
    @RequestMapping("/page")
    public Result page(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, HttpServletRequest req, StuCourse cs) {
        Result result = new Result();
        PageHelper.startPage(pageNo, pageHelper.pageSize, " id asc ");
        List<StuCourse> li = stuCourseService.list(cs);
        PageInfo<StuCourse> pageInfo = new PageInfo<StuCourse>(li, pageHelper.pageSize);
        result.setObj(pageInfo);
        result.setStatus(1);
        return result;

    }

    /**
     * web端 删除课程
     * 课程绑定教师 学生
     *
     * @param pageNo
     * @param model
     * @param req
     * @param cs
     * @return
     */
    @RequestMapping("/del")
    public String del(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, Model model, HttpServletRequest req, StuCourse cs) {

        String course_id = req.getParameter("course_id");

        Course c = courseService.getById(course_id);
        model.addAttribute("c", c);

        Teacher teach = teacherService.getById(c.getTeach_id() + "");
        model.addAttribute("teach", teach);

        String[] ids = req.getParameterValues("ids");
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                stuCourseService.delete(id);
            }
        }

        String id = req.getParameter("id");
        if (id != null && id.trim().length() > 0) {
            stuCourseService.delete(id);
        }

        StuCourse cs_con = new StuCourse();
        cs_con.setCourse_id(Integer.parseInt(course_id));
        List<StuCourse> csli = stuCourseService.list(cs_con);

        if (!(csli != null && csli.size() > 0)) {
            c.setHas_stu("0");
            courseService.update(c);
        }
        // 重定向页面
        return "redirect:/courseStu/page?course_id=" + course_id + "&pageNo=" + pageNo;

    }
}
