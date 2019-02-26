package com.aswishes.wn.spider.entity;

import java.util.Date;

import com.aswishes.spring.mapper.Mapper;
import com.aswishes.wn.mvc.model.BaseIdAuto;

@Mapper(tableName = "wn_spider_website", primaryKey = {"id"})
public class WnSpiderWebsite extends BaseIdAuto {

	private String name;
	private String url;
	
	@Mapper(name = "book_list_url_prefix")
	private String bookListUrlPrefix;
	
	@Mapper(name = "page_no")
	private Integer pageNo = 1;
	
	@Mapper(name = "book_list_url_suffix")
	private String bookListUrlSuffix;
	
	@Mapper(name = "book_list_charset")
	private String bookListCharset;
	
	@Mapper(name = "book_node_path")
	private String bookNodePath;
	
	@Mapper(name = "book_name_path")
	private String bookNamePath;
	
	@Mapper(name = "book_url_path")
	private String bookUrlPath;
	
	@Mapper(name = "total_page_path")
	private String totalPagePath;
	
	@Mapper(name = "total_page_express")
	private String totalPageExpress;
	
	@Mapper(name = "author_path")
	private String authorPath;
	
	@Mapper(name = "img_path")
	private String imgPath;
	
	@Mapper(name = "introduction_path")
	private String introductionPath;
	
	@Mapper(name = "last_update_time_path")
	private String lastUpdateTimePath;
	
	
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

	public String getBookListUrlPrefix() {
		return bookListUrlPrefix;
	}

	public void setBookListUrlPrefix(String bookListUrlPrefix) {
		this.bookListUrlPrefix = bookListUrlPrefix;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getBookListUrlSuffix() {
		return bookListUrlSuffix;
	}

	public void setBookListUrlSuffix(String bookListUrlSuffix) {
		this.bookListUrlSuffix = bookListUrlSuffix;
	}

	public String getBookListCharset() {
		return bookListCharset;
	}

	public void setBookListCharset(String bookListCharset) {
		this.bookListCharset = bookListCharset;
	}

	public String getBookNodePath() {
		return bookNodePath;
	}

	public void setBookNodePath(String bookNodePath) {
		this.bookNodePath = bookNodePath;
	}

	public String getBookNamePath() {
		return bookNamePath;
	}

	public void setBookNamePath(String bookNamePath) {
		this.bookNamePath = bookNamePath;
	}

	public String getBookUrlPath() {
		return bookUrlPath;
	}

	public void setBookUrlPath(String bookUrlPath) {
		this.bookUrlPath = bookUrlPath;
	}

	public String getTotalPagePath() {
		return totalPagePath;
	}

	public void setTotalPagePath(String totalPagePath) {
		this.totalPagePath = totalPagePath;
	}

	public String getTotalPageExpress() {
		return totalPageExpress;
	}

	public void setTotalPageExpress(String totalPageExpress) {
		this.totalPageExpress = totalPageExpress;
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

	public String getAuthorPath() {
		return authorPath;
	}

	public void setAuthorPath(String authorPath) {
		this.authorPath = authorPath;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getIntroductionPath() {
		return introductionPath;
	}

	public void setIntroductionPath(String introductionPath) {
		this.introductionPath = introductionPath;
	}

	public String getLastUpdateTimePath() {
		return lastUpdateTimePath;
	}

	public void setLastUpdateTimePath(String lastUpdateTimePath) {
		this.lastUpdateTimePath = lastUpdateTimePath;
	}
	
}
