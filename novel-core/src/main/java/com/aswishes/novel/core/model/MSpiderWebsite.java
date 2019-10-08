package com.aswishes.novel.core.model;

import java.util.Date;

import com.aswishes.spring.mapper.Mapper;

@Mapper(tableName = "m_spider_website", primaryKey = {"id"})
public class MSpiderWebsite extends BaseIdAuto {
	
	public static enum State {
		OPENED(1),
		CLOSED(2);
		private int value;
		private State(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}
	
	@Mapper(ignore = true)
	private String stateText;
	
	private String name;
	private String url;

	/** 状态：1 正常 2 关闭 */
	@Mapper(name = "state")
	private Integer state = 1;
	
	@Mapper(name = "rule_id")
	private Long ruleId;
	
	@Mapper(name = "last_retrive_name")
	private Date lastRetriveTime;
	
	@Mapper(name = "update_time")
	private Date updateTime;
	
	@Mapper(name = "create_time")
	private Date createTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getLastRetriveTime() {
		return lastRetriveTime;
	}

	public void setLastRetriveTime(Date lastRetriveTime) {
		this.lastRetriveTime = lastRetriveTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getStateText() {
		if (this.state == null || this.state.intValue() == 1) {
			this.stateText = "正常";
		} else if (this.state.intValue() == 2) {
			this.stateText = "关闭";
		}
		return stateText;
	}

	public void setStateText(String stateText) {
		this.stateText = stateText;
	}

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

}
