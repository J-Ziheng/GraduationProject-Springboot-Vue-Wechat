package com.ziheng.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ziheng.utils.pageHelper;
import com.ziheng.controller.result.Result;
import com.ziheng.domain.Major;
import com.ziheng.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * web端 专业管理
 */
@RestController
@RequestMapping("/major")
public class MajorController {
    @Autowired
    private MajorService majorService;

    @RequestMapping("/check_name")
    public Result check_name(Major t) {
        Result result = new Result();
        if (t.getId() != null) {
            List<Major> list = majorService.list(t);
            if (list != null && list.size() > 0) {
                Major s = list.get(0);
                System.out.println(s.getId() + "===kkk===" + t.getId());
                if (!s.getId().equals(t.getId())) {
                    result.setStatus(0);
                    result.setMsg("该名称重复");
                    return result;
                }
            }
        } else {
            List<Major> list = majorService.list(t);
            if (list != null && list.size() > 0) {
                result.setStatus(0);
                result.setMsg("该专业已添加，请勿重复添加");
                return result;
            }
        }
        result.setStatus(1);
        result.setMsg("该名称可用");
        return result;
    }


    @RequestMapping("/getList")
    public Result getList() {
        Result result = new Result();

        List<Major> list = majorService.list(new Major());
        System.out.println("这是major地list sql语句："+list);
        result.setStatus(1);
        result.setObj(list);
        return result;
    }


    @RequestMapping("/save")
    public Result save(Major m) {
        Result result = new Result();
        if (m.getId() == null) {
            majorService.insert(m);
            result.setStatus(1);
            result.setMsg("添加成功");

        } else {
            majorService.update(m);
            result.setStatus(1);
            result.setMsg("修改成功");
        }
        return result;
    }


    @RequestMapping("/page")
    public Result page(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo
            , Major m) {
        Result result = new Result();
        PageHelper.startPage(pageNo, pageHelper.pageSize, " id desc ");
        List<Major> li = majorService.list(m);
        PageInfo<Major> pageInfo = new PageInfo<Major>(li, pageHelper.pageSize);
        result.setStatus(1);
        result.setObj(pageInfo);
        return result;
    }


    @RequestMapping("/del")
    public Result del(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, Major m, Model model) {
        Result result = new Result();
        try {
            majorService.delete(m.getId() + "");
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
