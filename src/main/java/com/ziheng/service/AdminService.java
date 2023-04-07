package com.ziheng.service;


import com.ziheng.domain.Admin;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AdminService {
    /**
     * 登录接口
     * @param u
     * @return
     */
    Admin login(Admin u);

    /**
     * 注册
     * @param admin
     * @return
     */
    void reg(Admin admin);

    /**
     * 根据id获取管理员信息
     * @param id
     * @return
     */
    Admin getById(String id);

    /**
     * 更新管理员密码
     * @param admin
     */
    void update(Admin admin);


}
