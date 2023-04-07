package com.ziheng.controller.result;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于返回前端数据封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Integer status; // 1签到 2请假 3迟到 4旷课
    private String msg; // 返回前端与小程序消息
    private Object obj; // 返回小程序教师对象
    private Object obj2; // 返回小程序学生对象

}

