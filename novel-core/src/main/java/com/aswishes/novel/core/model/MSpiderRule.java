package com.aswishes.novel.core.model;

import java.util.Date;

import com.aswishes.spring.mapper.Mapper;

@Mapper(tableName = "m_spider_rule", primaryKey = {"id"})
public class MSpiderRule extends BaseIdAuto {

	@Mapper(name = "name")
	private String name;
	
	@Mapper(name = "book_list_url_format")
	private String bookListUrlFormat;
	
	@Mapper(name = "book_list_start_page_no")
	private String bookListStartPageNo;
	
	@Mapper(name = "book_list_charset")
	private String bookListCharset;
	
	@Mapper(name = "book_list_total_page_path")
	private String bookListTotalPagePath;
	
	@Mapper(name = "book_list_total_page_regular")
	private String bookListTotalPageRegular;

	@Mapper(name = "book_node_path")
	private String bookNodePath;
	
	@Mapper(name = "book_node_name_path")
	private String bookNodeNamePath;
	
	@Mapper(name = "book_node_url_path")
	private String bookNodeUrlPath;
	
	@Mapper(name = "book_node_author_path")
	private String bookNodeAuthorPath;
	
	@Mapper(name = "book_node_img_path")
	private String bookNodeImgPath;
	
	@Mapper(name = "book_node_introduction_path")
	private String bookNodeIntroductionPath;
	
	@Mapper(name = "book_node_last_update_time_path")
	private String bookNodeLastUpdateTimePath;
	
	@Mapper(name = "catalog_charset")
	private String catalogCharset;
	
	@Mapper(name = "catalog_chapter_node_path")
	private String catalogChapterNodePath;
	
	@Mapper(name = "catalog_chapter_url_path")
	private String catalogChapterUrlPath;
	
	@Mapper(name = "chapter_charset")
	private String chapterCharset;
	
	@Mapper(name = "chapter_node_path")
	private String chapterNodePath;
	
	@Mapper(name = "chapter_weed")
	private String chapterWeed;
	
	@Mapper(name = "update_time")
	private Date updateTime;
	
	@Mapper(name = "create_time")
	private Date createTime;

	public String getName() {
		return name;
	}

	public String getBookListUrlFormat() {
		return bookListUrlFormat;
	}

	public String getBookListStartPageNo() {
		return bookListStartPageNo;
	}

	public String getBookListCharset() {
		return bookListCharset;
	}

	public String getBookListTotalPagePath() {
		return bookListTotalPagePath;
	}

	public String getBookListTotalPageRegular() {
		return bookListTotalPageRegular;
	}

	public String getBookNodePath() {
		return bookNodePath;
	}

	public String getBookNodeNamePath() {
		return bookNodeNamePath;
	}

	public String getBookNodeUrlPath() {
		return bookNodeUrlPath;
	}

	public String getBookNodeAuthorPath() {
		return bookNodeAuthorPath;
	}

	public String getBookNodeImgPath() {
		return bookNodeImgPath;
	}

	public String getBookNodeIntroductionPath() {
		return bookNodeIntroductionPath;
	}

	public String getBookNodeLastUpdateTimePath() {
		return bookNodeLastUpdateTimePath;
	}

	public String getCatalogCharset() {
		return catalogCharset;
	}

	public String getCatalogChapterNodePath() {
		return catalogChapterNodePath;
	}

	public String getCatalogChapterUrlPath() {
		return catalogChapterUrlPath;
	}

	public String getChapterCharset() {
		return chapterCharset;
	}

	public String getChapterNodePath() {
		return chapterNodePath;
	}

	public String getChapterWeed() {
		return chapterWeed;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBookListUrlFormat(String bookListUrlFormat) {
		this.bookListUrlFormat = bookListUrlFormat;
	}

	public void setBookListStartPageNo(String bookListStartPageNo) {
		this.bookListStartPageNo = bookListStartPageNo;
	}

	public void setBookListCharset(String bookListCharset) {
		this.bookListCharset = bookListCharset;
	}

	public void setBookListTotalPagePath(String bookListTotalPagePath) {
		this.bookListTotalPagePath = bookListTotalPagePath;
	}

	public void setBookListTotalPageRegular(String bookListTotalPageRegular) {
		this.bookListTotalPageRegular = bookListTotalPageRegular;
	}

	public void setBookNodePath(String bookNodePath) {
		this.bookNodePath = bookNodePath;
	}

	public void setBookNodeNamePath(String bookNodeNamePath) {
		this.bookNodeNamePath = bookNodeNamePath;
	}

	public void setBookNodeUrlPath(String bookNodeUrlPath) {
		this.bookNodeUrlPath = bookNodeUrlPath;
	}

	public void setBookNodeAuthorPath(String bookNodeAuthorPath) {
		this.bookNodeAuthorPath = bookNodeAuthorPath;
	}

	public void setBookNodeImgPath(String bookNodeImgPath) {
		this.bookNodeImgPath = bookNodeImgPath;
	}

	public void setBookNodeIntroductionPath(String bookNodeIntroductionPath) {
		this.bookNodeIntroductionPath = bookNodeIntroductionPath;
	}

	public void setBookNodeLastUpdateTimePath(String bookNodeLastUpdateTimePath) {
		this.bookNodeLastUpdateTimePath = bookNodeLastUpdateTimePath;
	}

	public void setCatalogCharset(String catalogCharset) {
		this.catalogCharset = catalogCharset;
	}

	public void setCatalogChapterNodePath(String catalogChapterNodePath) {
		this.catalogChapterNodePath = catalogChapterNodePath;
	}

	public void setCatalogChapterUrlPath(String catalogChapterUrlPath) {
		this.catalogChapterUrlPath = catalogChapterUrlPath;
	}

	public void setChapterCharset(String chapterCharset) {
		this.chapterCharset = chapterCharset;
	}

	public void setChapterNodePath(String chapterNodePath) {
		this.chapterNodePath = chapterNodePath;
	}

	public void setChapterWeed(String chapterWeed) {
		this.chapterWeed = chapterWeed;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
