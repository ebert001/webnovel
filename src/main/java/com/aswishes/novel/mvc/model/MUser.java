package com.aswishes.novel.mvc.model;

import java.util.Date;

import com.aswishes.spring.mapper.Mapper;

/**
 * 用户表.
 * 可以使用 邮箱+密码 / 用户+密码 / 手机+密码 登录
 * 权限模型: 用户角色权限
 */
@Mapper(tableName = "m_user", primaryKey = {"id"})
public class MUser extends BaseIdAuto {
	/** 用户名 */
	private String name;
	/** 邮件 */
	private String email;
	/** 手机 */
	private String phone;
	/** 密码 */
	private String pwd;
	/** 别名 */
	private String alias;
	/** 生日 */
	private Date birthday;
	/** 备注 */
	private String remark;
	/** 状态 */
	private Integer state;
	/** 性别 */
	private Integer sex;
	/** 最后登录时间 */
	@Mapper(name = "last_login_time")
	private Date lastLoginTime;
	/** 盐 */
	private String salt;
	/** 密码生成算法 */
	private String alg;
	/** 注册时间 */
	@Mapper(name = "reg_time")
	private Date regTime;
	/** 角色 */
	@Mapper(ignore = true)
	private String role;
	
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

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getAlg() {
		return alg;
	}

	public void setAlg(String alg) {
		this.alg = alg;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
