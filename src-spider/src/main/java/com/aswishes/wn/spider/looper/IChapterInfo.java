package com.aswishes.wn.spider.looper;

public interface IChapterInfo {
	void extractBookInfo(BookInfo bookInfo);
	boolean extract(ChapterInfo info);
	void extractContent(ChapterInfo info, String content);
}
