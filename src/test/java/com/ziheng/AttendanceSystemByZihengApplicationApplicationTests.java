package com.ziheng;

import com.sun.DateUtils;
import com.ziheng.dao.MajorMapper;
import com.ziheng.domain.Major;
import com.ziheng.domain.Teacher;
import com.ziheng.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class AttendanceSystemByZihengApplicationApplicationTests {
    @Resource
    private TeacherService teacherService;

    @Test
    void testDateUtils() {
        String cts = DateUtils.getNowDateTimeString();
        System.out.println(cts);
//         2023-03-07 14:16:07

        String today = DateUtils.getNowDateString();
        System.out.println(today);
//        2023-03-07


        String hm = DateUtils.getNowHMString();
        System.out.println(hm);
//        14:16
    }
    @Test
    void testNewDateTime(){
        long nts = new Date().getTime();
        System.out.println(nts);
        // 1680848504875 (还记得时间吗，计算机的时间起点是六万多秒还是多少来着，在学习日期类的时候，时间以此为起点计算)
    }
    @Test
    void testMajorMapperList(){
        List<Teacher> teacherList = teacherService.selectRealname();
//        for (Teacher teacher : teacherList) {
//            System.out.println(teacher.toString());
//        }
//        System.out.println("-----------------------------------------");
        String teacherName="";
        for (int j = 0; j < teacherList.size(); j++) {
            Teacher teacher = teacherList.get(j);
            teacherName = teacher.getRealname();
            System.out.println(teacherName);
            String n = teacherName;
            System.out.println(n);
        }
        System.out.println("-----------------------------------------");
        String n = teacherName;
        System.out.println(n);
    }

}
