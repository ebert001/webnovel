package com.aswishes.wn.spider;

public class ChapterInfo {

	private String chapterUrl;
	private String chapterTitle;
	private String chapterContent;
	
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("title  : ").append(chapterTitle).append("\n");
		sb.append("url    : ").append(chapterUrl).append("\n");
		sb.append("content: ").append(chapterContent).append("\n");
		return sb.toString();
	}
	
}
