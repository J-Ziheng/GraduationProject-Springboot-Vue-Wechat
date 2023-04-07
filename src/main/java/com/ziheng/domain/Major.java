package com.ziheng.domain;

/**
 * 专业
 */
public class Major {//专业
	private Integer id;
	private String major_name;
	private Integer system_id;
	/*以下字段用来传值*/
	private String sys_name;//院系名称

	public Major() {
	}

	public Integer getSystem_id() {
		return system_id;
	}

	public void setSystem_id(Integer system_id) {
		this.system_id = system_id;
	}
	public String getSys_name() {
		return sys_name;
	}
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}
	public String getMajor_name() {
		return major_name;
	}
	public void setMajor_name(String major_name) {
		this.major_name = major_name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	 
	 
	
}
