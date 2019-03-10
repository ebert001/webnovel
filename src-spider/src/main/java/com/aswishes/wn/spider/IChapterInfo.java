package com.aswishes.wn.spider;

public interface IChapterInfo {
	boolean extract(ChapterInfo info);
	void extractContent(ChapterInfo info, String content);
}
