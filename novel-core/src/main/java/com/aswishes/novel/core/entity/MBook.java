package com.aswishes.novel.core.model;

import java.util.Date;

import com.aswishes.novel.common.db.Mapper;

/**
 * 书籍表。存储所有的书籍信息，并对书籍进行分类
 */
@Mapper(tableName = "m_book", primaryKey = {"id"})
public class MBook extends BaseIdAuto {
	
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
	
	public enum RetriveState {
		FINISHED(1),
		WAIT(2),
		RETRIVING(3),
		FAILED(3);
		private int value;
		private RetriveState(int value) {
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}
	
	
	/** 书籍名称 */
	@Mapper(name= "name")
	private String name;
	
	/** 书籍描述 */
	@Mapper(name= "introduction")
	private String introduction;
	
	/** 作者id */
	@Mapper(name= "author_id")
	private Long authorId;
	
	@Mapper(name= "author")
	private String author;
	
	private String img;
	
	@Mapper(name = "last_chapter_url")
	private String lastChapterUrl;
	
	/** 创作时间 */
	@Mapper(name= "create_time")
	private Date createTime;
	
	/** 更新时间 */
	@Mapper(name= "update_time")
	private Date updateTime;
	
	private String url;
	
	@Mapper(name= "retrive_count")
	private Integer retriveCount;
	
	// 检索状态 1 检索完成(成功) 2 等待检索 3 正在检索 4 检索失败
	@Mapper(name= "retrive_state")
	private Integer retriveState;
	
	@Mapper(name= "retrive_start_time")
	private Date retriveStartTime;
	
	@Mapper(name= "retrive_stop_time")
	private Date retriveStopTime;
	
	@Mapper(name= "retrive_fail_cause")
	private String retriveFailCause;
	
	/** 书籍状态 1 正常 2 关闭 3 未审核 */
	private Integer state;
	
	/** 状态：1、写作中，2、已完结 */
	@Mapper(name = "serialize_status")
	private Integer serializeStatus;
	
	@Mapper(name = "tags")
	private String tags;
	
	/** 字数 */
	private Integer words;
	
	/** 是否收费 */
	private Integer charged;
	
	/** 费用, 单位: 分 */
	private Integer charge;
	
	/** 点击次数 */
	@Mapper(name= "click_times")
	private Integer clickTimes;
	
	/** 评论次数 */
	@Mapper(name= "comment_times")
	private Integer commentTimes;
	
	@Mapper(name = "website_id")
	private Long websiteId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getLastChapterUrl() {
		return lastChapterUrl;
	}

	public void setLastChapterUrl(String lastChapterUrl) {
		this.lastChapterUrl = lastChapterUrl;
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

	public Integer getRetriveCount() {
		return retriveCount;
	}

	public void setRetriveCount(Integer retriveCount) {
		this.retriveCount = retriveCount;
	}

	public Integer getRetriveState() {
		return retriveState;
	}

	public void setRetriveState(Integer retriveState) {
		this.retriveState = retriveState;
	}

	public Date getRetriveStartTime() {
		return retriveStartTime;
	}

	public void setRetriveStartTime(Date retriveStartTime) {
		this.retriveStartTime = retriveStartTime;
	}

	public Date getRetriveStopTime() {
		return retriveStopTime;
	}

	public void setRetriveStopTime(Date retriveStopTime) {
		this.retriveStopTime = retriveStopTime;
	}

	public String getRetriveFailCause() {
		return retriveFailCause;
	}

	public void setRetriveFailCause(String retriveFailCause) {
		this.retriveFailCause = retriveFailCause;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getSerializeStatus() {
		return serializeStatus;
	}

	public void setSerializeStatus(Integer serializeStatus) {
		this.serializeStatus = serializeStatus;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Integer getWords() {
		return words;
	}

	public void setWords(Integer words) {
		this.words = words;
	}

	public Integer getCharged() {
		return charged;
	}

	public void setCharged(Integer charged) {
		this.charged = charged;
	}

	public Integer getCharge() {
		return charge;
	}

	public void setCharge(Integer charge) {
		this.charge = charge;
	}

	public Integer getClickTimes() {
		return clickTimes;
	}

	public void setClickTimes(Integer clickTimes) {
		this.clickTimes = clickTimes;
	}

	public Integer getCommentTimes() {
		return commentTimes;
	}

	public void setCommentTimes(Integer commentTimes) {
		this.commentTimes = commentTimes;
	}

	public Long getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(Long websiteId) {
		this.websiteId = websiteId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
