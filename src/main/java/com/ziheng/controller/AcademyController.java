package com.ziheng.controller;

import java.util.List;

import com.ziheng.controller.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ziheng.utils.pageHelper;
import com.ziheng.domain.Academy;
import com.ziheng.service.AcademyService;
import org.springframework.web.bind.annotation.RestController;

/**
 * web端 院系管理
 */
@RestController
@RequestMapping("/system")
public class AcademyController {
    @Autowired
    private AcademyService academyService;

    @RequestMapping("/check_name")
    public Result check_name(Academy t) {
        Result result = new Result();
        if (t.getId() != null) {

            List<Academy> list = academyService.list(t);
            if (list != null && list.size() > 0) {
                Academy s = list.get(0);
                System.out.println(s.getId() + "===kkk===" + t.getId());
                if (!s.getId().equals(t.getId())) {
                    result.setStatus(0);
                    result.setMsg("该名称重复");
                    return result;
                }

            }
        } else {
            List<Academy> list = academyService.list(t);
            if (list != null && list.size() > 0) {
                result.setStatus(0);
                result.setMsg("该院系已添加，请勿重复添加");
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
        List<Academy> list = academyService.list(new Academy());
        result.setStatus(1);
        result.setObj(list);
        return result;
    }

    @RequestMapping("/save")
    public Result save(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, Academy s) {

        Result result = new Result();
        if (s.getId() == null) {
            academyService.insert(s);
            result.setStatus(1);
            result.setMsg("添加成功");

        } else {

            academyService.update(s);
            result.setStatus(1);
            result.setMsg("修改成功");
        }
        return result;
    }

    @RequestMapping("/page")
    public Result page(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo
            , Academy s) {
        Result result = new Result();
        PageHelper.startPage(pageNo, pageHelper.pageSize, " id desc ");
        List<Academy> li = academyService.list(s);
        PageInfo<Academy> pageInfo = new PageInfo<Academy>(li, pageHelper.pageSize);
        result.setObj(pageInfo);
        return result;
    }

    @RequestMapping("/del")
    public Result del(Academy s) {
        Result result = new Result();
        try {
            academyService.delete(s.getId() + "");
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
