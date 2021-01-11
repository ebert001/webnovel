package com.aswishes.novel.core.entity;

import java.util.Date;

import com.aswishes.novel.common.db.Mapper;

/**
 * 作品章节
 */
@Mapper(tableName = "m_chapter", primaryKey = {"id"})
public class MChapter extends BaseIdAuto {
	
	public enum State {
		NORMALE(1),
		CLOSED(2),
		UNAUDITED(3);
		private int value;
		private State(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}
	
	/** 书籍id */
	@Mapper(name = "book_id")
	private Long bookId;
	
	/** 分卷id */
	@Mapper(name = "volume_id")
	private Long volumeId;
		
	/** 章节名称 */
	private String subject;
	
	/** 章节内容 */
	private String content;
	
	/** 章节序列号 */
	@Mapper(name = "serial_no")
	private int serialNo;
	
	/** 是否收费 */
	private Integer charged = 0;
	
	/** 写作时间 */
	@Mapper(name = "write_time")
	private Date writeTime;
	
	/** 录入时间 */
	@Mapper(name = "input_time")
	private Date inputTime;
	
	/** 书籍状态 1 正常 2 关闭 3 未审核  */
	private Integer state;

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

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getVolumeId() {
		return volumeId;
	}

	public void setVolumeId(Long volumeId) {
		this.volumeId = volumeId;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
