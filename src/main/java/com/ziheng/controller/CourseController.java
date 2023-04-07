package com.ziheng.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ziheng.domain.Teacher;
import com.ziheng.utils.pageHelper;
import com.ziheng.controller.result.Result;
import com.ziheng.service.*;
import com.ziheng.domain.Course;
import com.ziheng.domain.StuCourse;
import com.ziheng.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * web端 课程管理
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private StuCourseService stuCourseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    /**
     * web端 课程管理 保存和修改模块
     *
     * @param c
     * @return
     */
    @RequestMapping("/save")
    public Result save(Course c) {
        Result result = new Result();

        if (c.getId() != null) {
            StuCourse stuCourse = new StuCourse();
            stuCourse.setCourse_id(c.getId());
            List<StuCourse> list = stuCourseService.list(stuCourse);
            if (list != null && list.size() > 0) {
                for (StuCourse e : list) {
                    stuCourseService.delete(e.getId() + "");
                }

            }
            courseService.update(c);
            result.setStatus(1);
            result.setMsg("修改成功");
        } else {
            courseService.insert(c);
            result.setStatus(1);
            result.setMsg("保存成功");

        }
        //添加学生和课程中间表(先查该专业下的学生)
        Student student = new Student();
        student.setMajor_id(c.getMajor_id());
        List<Student> stuList = studentService.list(student);
        if (stuList != null && stuList.size() > 0) {
            c.setHas_stu("1");
            courseService.update(c);
            for (Student stu : stuList) {
                StuCourse es = new StuCourse();
                es.setStu_id(stu.getId());
                es.setCourse_id(c.getId());
                stuCourseService.insert(es);
            }
        }
        return result;
    }
    /**
     * 获取所有课程信息模块
     *
     * @param o
     * @return
     */
    @RequestMapping("/getAll")
    public Result getAll(Course o) {
        Result result = new Result();
        List<Course> list = courseService.list(o);
        result.setObj(list);
        return result;
    }
    /**
     * 课程信息查询  &  分页
     * 与mounted生命周期函数绑定
     * @param pageNo
     * @param c
     * @return
     */
    @RequestMapping("/page")
    public Result page(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo
            , Course c) {
        Result result = new Result();
        // 一定要从小到大正序排序，否则会导致下面修复bug的代码，让教师和课程倒着注入，搞反了
        PageHelper.startPage(pageNo, pageHelper.pageSize,"id asc");
        // 倒叙排序（分页工具）
//        PageHelper.startPage(pageNo, pageHelper.pageSize，"id desc");
        List<Course> li = courseService.list(c);

        // TODO 子恒：(已修复)修复web端课程管理模块 查询课程管理 得不到教师姓名的bug(dao层 service层以及这里目前已经修复完成，可以注入教师的realname)
        // 原因：因为course表没有realname，只能通过定义Course的realname属性传值，而realname属性经过测试发现，在这里请求获取数据的时候，并没有获取realname的数据
        // 经过数个小时的排查发现：realname没有注入教师的姓名外，也没有为底层设置教师姓名的内连接查询，课程的teacher_id=教师id
        // dao、service、impl均已修复和实现 没有内连接查询realname的bug，并在此调用impl的selectRealname方法将教师姓名realname注入进Course的realname属性中
        // 这样，web端课程管理的生命周期mounted再获取课程数据就可以获取到教师的姓名了
        // 获取与每一个课程绑定的教师对象集合
        List<Teacher> teacherList = teacherService.selectRealname();
        for (int i = 0; i <  teacherList.size(); i++) {
            // 从集合中一次拿取一个教师对象
            Teacher teacher = teacherList.get(i);
            // 一次获取一个教师姓名
            String tname = teacher.getRealname();
            Course course = li.get(i);
            // course对象的realname一次赋值一个
            course.setRealname(tname);

//            for (int j = 0; j < li.size(); j++) {
//                // List<Course>集合跟随teacherList集合的i，一次拿取一个course对象
//                Course course = li.get(i);
//                // course对象的realname一次赋值一个
//                course.setRealname(tname);
//            }
            // 综上，如此逐一获取赋值，解决web端获取course对象的realname数据为null的问题
        }

        PageInfo<Course> pageInfo = new PageInfo<Course>(li, pageHelper.pageSize);
        result.setObj(pageInfo);
        return result;
    }

    /**
     * 课程信息删除模块
     *
     * @param c
     * @return
     */
    @RequestMapping("/del")
    public Result del(Course c) {
        Result result = new Result();
        try {
            courseService.delete(c.getId() + "");
            //先把该课程下的学生和课程中间表的记录删掉
            StuCourse stuCourse = new StuCourse();
            stuCourse.setCourse_id(c.getId());
            List<StuCourse> list = stuCourseService.list(stuCourse);
            if (list != null && list.size() > 0) {
                for (StuCourse e : list) {
                    stuCourseService.delete(e.getId() + "");
                }
            }
            result.setStatus(1);
            result.setMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(0);
            result.setMsg("删除失败");
        }
        return result;

    }

    //根据ID获取详情
    @RequestMapping("/info")
    public Result info(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, HttpServletRequest req, Course c) {
        Result result = new Result();
        c = courseService.getById(c.getId() + "");
        result.setObj(c);
        result.setStatus(1);
        return result;

    }
}
