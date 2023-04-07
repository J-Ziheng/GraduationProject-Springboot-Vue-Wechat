package com.ziheng.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ziheng.dao.AdminMapper;
import com.ziheng.domain.Admin;
import com.ziheng.service.AdminService;
import org.springframework.transaction.annotation.Transactional;

/**
 *  @author ziheng.com
 *
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminMapper adminMapper;

	/**
	 * 管理员登录
	 * @param admin
	 * @return
	 */
	@Override
	public Admin login(Admin admin) {
		return adminMapper.login(admin);
	}

	/**
	 * 管理员注册
	 * @param admin
	 * @return
	 */
	@Override
	public void reg(Admin admin) {
		adminMapper.insert(admin);
	}

	/**
	 * 查询管理员
	 * @param id
	 * @return
	 */
	@Override
	public Admin getById(String id) {
		return adminMapper.getById(id);
	}

	@Override
	public void update(Admin admin) {
		adminMapper.update(admin);
	}

	 
	 

	 
}
