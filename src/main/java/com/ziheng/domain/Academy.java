package com.ziheng.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 学院
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Academy {
	private Integer id;
	private String sys_name;
	


	public String getSys_name() {
		return sys_name;
	}
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	 
	 
	
}
