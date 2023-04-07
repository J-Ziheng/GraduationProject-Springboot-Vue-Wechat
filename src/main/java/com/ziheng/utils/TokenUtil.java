package com.ziheng.utils;

 
import java.util.UUID;
import com.sun.DateUtils;


/**
 *  @author ziheng.com
 *
 */
public class TokenUtil {
     public static String get() {
         /*我们可以使用UUID类来生成随机文件名，会话或事务ID。 UUID的另一种流行用法是在数据库中生成主键值。*/
         /*https://blog.csdn.net/cunfen3485/article/details/112545174*/
    	 String s=DateUtils.getNowTs()+UUID.randomUUID().toString().replace("-", "");
    	 return s;
     }
}

