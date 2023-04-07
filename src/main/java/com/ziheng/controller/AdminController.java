package com.ziheng.controller;

import com.ziheng.controller.result.Result;
import com.ziheng.domain.Admin;
import com.ziheng.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * web端 登陆页面
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 登录功能
     *
     * @param admin
     * @return
     */
    @RequestMapping("/login")
    public Result login(Admin admin) {
        Result result = new Result();
        if (admin.getUname() == null || admin.getUname().isEmpty()) {//  账号用户名为空判断
            result.setStatus(0);
            result.setMsg("请输入用户名");
            return result;
        }
        if (admin.getUpass() == null || admin.getUpass().isEmpty()) {//  账号密码为空判断
            result.setStatus(0);
            result.setMsg("请输入密码");
            return result;
        }
        admin = adminService.login(admin);
        if (admin != null) {
            result.setStatus(1);// 登录成功
            result.setMsg("登录成功");
            result.setObj(admin);
            return result;
        } else {// 登陆失败
            result.setStatus(0);
            result.setMsg("账号或者密码错误");
            return result;
        }
    }

    /**
     * 注册
     *
     * @param admin
     * @return
     */
    @RequestMapping("/reg")
    public Result reg(Admin admin) {
        Result result = new Result();
        if (admin != null) {
            Admin reg = new Admin();
            reg.setUpass(admin.getUpass());
            reg.setUname(admin.getUname());
            System.out.println(reg);
            adminService.reg(reg);
            result.setMsg("注册成功");
            result.setStatus(1);
        } else {
            result.setMsg("注册失败");
            result.setStatus(0);
        }
        return result;
    }

    /**
     * 修改密码
     *
     * @param admin
     * @param req
     * @return
     */
    @RequestMapping("/updatePwd")
    public Result updatePwd(Admin admin, HttpServletRequest req) {
        Result result = new Result();
        String old_upass = req.getParameter("old_upass");// 旧密码
        String new_upass = req.getParameter("new_upass");// 新密码
        String new_upass2 = req.getParameter("new_upass2");// 二次新密码

        admin = adminService.getById(admin.getId() + "");// 获取数据库管理员密码
        if (!admin.getUpass().equals(old_upass)) {// 数据库的密码与输入的旧密码比对
            result.setStatus(0);
            result.setMsg("您的原始密码输入错有误,请重新输入");
            return result;
        }
        if (!new_upass.equals(new_upass2)) {// 新密码确认比对
            result.setStatus(0);
            result.setMsg("您的密码输入不一致，请重新输入");
            return result;
        }
        admin.setUpass(new_upass);
        adminService.update(admin);//	更新密码
        result.setStatus(1);
        result.setMsg("您的密码修改成功，请妥善保存密码");
        return result;
    }


}
