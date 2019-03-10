package com.aswishes.wn.spider;

public class ChapterInfo {

	private String chapterUrl;
	private String chapterTitle;
	private String chapterContent;
	private String deployTime;
	private int serialNo;
	
	public String getChapterUrl() {
		return chapterUrl;
	}
	public void setChapterUrl(String chapterUrl) {
		this.chapterUrl = chapterUrl;
	}
	public String getChapterTitle() {
		return chapterTitle;
	}
	public void setChapterTitle(String chapterTitle) {
		this.chapterTitle = chapterTitle;
	}
	public String getChapterContent() {
		return chapterContent;
	}
	public void setChapterContent(String chapterContent) {
		this.chapterContent = chapterContent;
	}
	public String getDeployTime() {
		return deployTime;
	}
	public void setDeployTime(String deployTime) {
		this.deployTime = deployTime;
	}
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("title  : ").append(chapterTitle).append("\n");
		sb.append("url    : ").append(chapterUrl).append("\n");
		sb.append("content: ").append(chapterContent).append("\n");
		return sb.toString();
	}
	
}
