package com.aswishes.wn.mvc.model;

import java.util.Date;

/**
 * 用户表
 */
public class WnUser {
	
	/** 用户名 */
	private String name;
	
	/** 密码 */
	private String pwd;
	
	/** 别名 */
	private String alias;
	
	/** 注册时间 */
	private Date regTime;
	
	/** 邮件 */
	private String email;
	
	/** 手机 */
	private String phone;
	
	/** 角色 */
	private Integer role;
	
	/** 权限 */
	private Integer permission;
	
	/** 生日 */
	private Date birthday;
	
	/** 备注 */
	private String remark;
	
	/** 状态 */
	private Integer status;
	
	/** 性别 */
	private Integer sex;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	
}
