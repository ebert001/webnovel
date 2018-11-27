package com.aswishes.wn.mvc.model;

import java.util.Date;

/**
 * 作品章节
 */
public class WnChapter {

	/** 主键 */
	private String id;
	
	/** 章节名称 */
	private String subject;
	
	/** 章节内容 */
	private String content;
	
	/** 章节序列号 */
	private int serialNo;
	
	/** 是否收费 */
	private Integer charged;
	
	/** 写作时间 */
	private Date writeTime;
	
	/** 录入时间 */
	private Date inputTime;
	
	/** 分卷id */
	private String volumeId;
	
	/** 书籍id */
	private String bookId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCharged() {
		return charged;
	}

	public void setCharged(Integer charged) {
		this.charged = charged;
	}

	public Date getWriteTime() {
		return writeTime;
	}

	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getVolumeId() {
		return volumeId;
	}

	public void setVolumeId(String volumeId) {
		this.volumeId = volumeId;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	
	
}
