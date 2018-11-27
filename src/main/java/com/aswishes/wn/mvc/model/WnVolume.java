package com.aswishes.wn.mvc.model;

import java.util.Date;

/**
 * 书籍分卷信息
 */
public class WnVolume {

	/** 主键 */
	private String id;
	
	/** 卷名称 */
	private String volumeName;
	
	/** 是否收费 */
	private Integer charged;
	
	/** 创建时间 */
	private Date createTime;
	
	/** 最后更新时间 */
	private Date updateTime;
	
	/** 字数 */
	private Integer words;
	
	/** 书籍id */
	private String bookId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVolumeName() {
		return volumeName;
	}

	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}

	public Integer getCharged() {
		return charged;
	}

	public void setCharged(Integer charged) {
		this.charged = charged;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getWords() {
		return words;
	}

	public void setWords(Integer words) {
		this.words = words;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	
}
