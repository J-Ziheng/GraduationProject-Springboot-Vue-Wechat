package com.ziheng.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ziheng.utils.pageHelper;
import com.ziheng.controller.result.Result;
import com.ziheng.domain.Major;
import com.ziheng.domain.Student;
import com.ziheng.service.MajorService;
import com.ziheng.service.StuAttendanceInfoService;
import com.ziheng.service.StudentService;
import com.ziheng.utils.TokenUtil;
import com.ziheng.utils.face.FaceData;
import com.ziheng.utils.face.FaceUtils;
import com.ziheng.utils.face.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学生管理
 * Web管理端&小程序端登录、修改密码
 * <p>
 * 采集学生人脸数据
 * 学生的C R U D 、login
 */
@RestController
@RequestMapping("/stu")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private StuAttendanceInfoService stuAttendanceInfoService;


    /**
     * 这个暂时不用
     *
     * Web管理端 学生注册验证
     *
     * @Param s 学生
     */
    @RequestMapping("/check_stu_no")
    public Result check_stu_no(Student s) {
        Result result = new Result();

        if (s.getId() != null) {
            Student byId = studentService.getById(s.getId() + "");
            if (byId.getStu_no().equals(s.getStu_no())) {
                result.setStatus(1);
                result.setMsg("该编号可用");
                return result;
            }
        }
        s = studentService.getByStuNo(s.getStu_no());
        if (s != null) {
            result.setStatus(0);
            result.setMsg("该学号已注册，请换一个");
        } else {
            result.setStatus(1);
            result.setMsg("该学号可用");
        }
        return result;
    }

    /**
     * Web管理端 获取学生专业
     *
     * @param s 学生
     * @return
     */
    @RequestMapping("/getMajors")
    public Result getMajors(Student s) {
        Result result = new Result();
        Major major = new Major();
        major.setSystem_id(s.getSys_id());

        List<Major> majors = majorService.list(major);

        Map<String, Object> m = new HashMap<String, Object>();
        if (majors == null) {
            result.setStatus(0);
            result.setObj(null);
        } else {
            result.setStatus(1);
            result.setObj(majors);
        }
        return result;
    }

    /**
     * Web管理端 添加学生 并 设置初始密码
     *
     * @param request http请求暂不使用，前后端跨域问题已通过拦截器解决
     * @param s       学生
     * @return
     */
    @RequestMapping("/save")
    public Result save(HttpServletRequest request, Student s) {
        Result result = new Result();
        if (s.getId() == null) {
            Student ct = studentService.getByStuNo(s.getStu_no());
            if (ct != null) {
                result.setMsg(s.getStu_no() + "学号已注册，添加失败");
                result.setStatus(0);
                return result;
            } else {
                // 设置初始密码为后4位
                s.setUpass(s.getStu_no().substring(s.getStu_no().length() - 4, s.getStu_no().length()));
                studentService.insert(s);
                result.setMsg("添加成功");
                result.setStatus(1);
                return result;
            }
        } else {
            studentService.update(s);
            result.setMsg("修改成功");
            result.setStatus(1);
            return result;
        }

    }

    /**
     * Web管理端 学生 分页
     *
     * @param pageNo
     * @param s
     * @param model
     * @return
     */
    @RequestMapping("/page")
    public Result page(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo
            , Student s, Model model) {
        Result result = new Result();
//        PageHelper.startPage(pageNo, pageHelper.pageSize, " id desc ");
        PageHelper.startPage(pageNo, pageHelper.pageSize, " id asc ");
        List<Student> li = studentService.list(s);
        PageInfo<Student> pageInfo = new PageInfo<Student>(li, pageHelper.pageSize);

        result.setStatus(1);
        result.setObj(pageInfo);
        return result;
    }

    /**
     * Web管理端 学生删除
     *
     * @param pageNo
     * @param request http请求暂不使用，前后端跨域问题已通过拦截器解决
     * @param s
     * @param model
     * @return
     */
    @RequestMapping("/del")
    public Result del(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, HttpServletRequest request, Student s, Model model) {
        Result result = new Result();
        try {
            studentService.delete(s.getId() + "");
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
     * Web管理端 学生密码初始化 初始化为学号后4位
     *
     * @param pageNo
     * @param s
     * @param model
     * @return
     */
    @RequestMapping("/init_pwd")
    public Result init_pwd(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, Student s, Model model) {
        Result result = new Result();
        // 根据id获取学生对象
        s = studentService.getById(s.getId() + "");
        // 设置该学生密码 通过学生学号来subString截取 后四位，9-4=5 截取包含第五位数字后面的(包前不包后)
        // 195150116 -- 第五位--> 0 : 0116
        s.setUpass(s.getStu_no().substring(s.getStu_no().length() - 4, s.getStu_no().length()));
        studentService.update(s);
        result.setStatus(1);
        result.setMsg("密码初始化为学号后4位");
        return result;
    }

    /**
     * 小程序端 学生小程序登陆
     *
     * @param s
     * @return
     */

    @RequestMapping("/login")
    public Result login(Student s) {
//方法参数： Model model, HttpSession session取消使用 登录用不到~
        Result result = new Result();
        if (s.getStu_no() == null || s.getStu_no().isEmpty()) {
            result.setMsg("请输入学号");
            result.setStatus(0);
            return result;
        }
        if (s.getUpass() == null || s.getUpass().isEmpty()) {
            result.setMsg("请输入密码");
            result.setStatus(0);
            return result;
        }
        List<Student> li = studentService.list(s);
        if (li != null && li.size() > 0) {
            s = li.get(0);
            // 登陆成功 添加token
            s.setToken(TokenUtil.get());
            studentService.update(s);
            result.setMsg("登录成功");
            result.setObj(s);
            result.setStatus(1);
            return result;
        } else {
            result.setStatus(0);
            result.setMsg("账号或者密码错误");
            return result;
        }

    }

    /**
     * 小程序端  修改学生密码
     *
     * @param a
     * @param req
     * @return
     */
    @RequestMapping("/updatePwd")
    public Result updatePwd(Student a, HttpServletRequest req) {
        Result result = new Result();
        String old_upass = req.getParameter("oldp");
        String new_upass = req.getParameter("newp");
        String new_upass2 = req.getParameter("newp2");

        a = studentService.getById(a.getId() + "");
        if (!a.getUpass().equals(old_upass)) {
            result.setStatus(0);
            result.setMsg("原密码不对,修改密码失败");
            return result;
        }
        if (!new_upass.equals(new_upass2)) {
            result.setStatus(0);
            result.setMsg("两次密码输入不一致,修改密码失败");
            return result;
        }
        a.setUpass(new_upass);
        studentService.update(a);
        result.setStatus(1);
        result.setMsg("密码修改成功");
        return result;

    }

    /**
     * web管理端  添加学生人脸特征值-采集人脸信息到数据库
     *
     * @param a
     * @param img
     * @return
     */
    @RequestMapping("/addFaceData")
    public Object addFaceData(Student a, String img) {
        // 使用map集合，
        Map<String, Object> map = new HashMap<String, Object>();
        byte[] bytes = ImageUtils.base64ToByte(img);//base64位图转成byte[]
        FaceData faceData = null;
        try {
            faceData = FaceUtils.getFaceData(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
            map.put("msg", "未检测到人脸-请正对摄像头重新识别-也可能你的浏览器没唤起摄像头");
            return map;
        }
        //判断是否检测到人脸
        if (faceData == null || faceData.getValidateFace() != 0) {
            map.put("status", 0);
            map.put("msg", "人脸检测失败-请正对摄像头");
            return map;
        } else if (faceData.getValidatePoint() != 0) {
            map.put("status", 0);
            map.put("msg", "获取人脸特征值失败-请重新采集");
            return map;
        } else {
            map.put("status", 1);
            map.put("msg", "您的人脸采集成功");
            a.setFace_data(faceData.getFaceData());
            a.setIs_face("1");
            studentService.update(a);
            return map;
        }
    }

}
