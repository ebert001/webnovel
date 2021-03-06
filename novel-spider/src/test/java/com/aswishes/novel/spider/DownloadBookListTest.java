package com.aswishes.novel.spider;

import com.aswishes.novel.spider.looper.PickBooks;

public class DownloadBookListTest {

	public static void main(String[] args) throws Exception {
		// 领域文学爬取
		// "https://www.lingyu.org/wjsw/list-1-1.html"
//		new DonovelloadBookList("https://www.lingyu.org/wjsw/list-1-", 1, ".html")
//			.setBookListCharset("GBK")
//			.setShowDebug(false)
//			.setBookNodePath("//div[@class='tt']")
//			.setBookNamePath("h3/a")
//			.setBookUrlPath("h3/a/@href")
//			.setTotalPagePath("//div[@class='pagelink']/a[@class='last']")
//			.discovery();
		
		// 千千小说
		// https://www.qqxsw.co/toptime/
		PickBooks bean = new PickBooks("https://www.qqxsw.co/toptime/{0}.html", 1);
		bean.setBookListCharset("GBK");
		bean.setShowDebug(false);
		bean.setBookNodePath("//div[@id='alist']//div[@id='alistbox']");
		bean.setBookNodeNamePath(".//div[@class='title']/h2/a");
		bean.setBookNodeUrlPath(".//div[@class='title']/h2/a/@href");
		bean.setTotalPagePath("//div[@class='pagelink']/a[@class='last']/@href");
		bean.setTotalPageExpress("\\d+");
		bean.discovery();
	}
}
