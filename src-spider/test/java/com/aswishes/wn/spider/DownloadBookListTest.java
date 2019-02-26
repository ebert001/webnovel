package com.aswishes.wn.spider;

import org.junit.Test;

public class DownloadBookListTest {

	@Test
	public void testDiscovery() throws Exception {
		// 领域文学爬取
		// "https://www.lingyu.org/wjsw/list-1-1.html"
//		new DownloadBookList("https://www.lingyu.org/wjsw/list-1-", 1, ".html")
//			.setBookListCharset("GBK")
//			.setShowDebug(false)
//			.setBookNodePath("//div[@class='tt']")
//			.setBookNamePath("h3/a")
//			.setBookUrlPath("h3/a/@href")
//			.setTotalPagePath("//div[@class='pagelink']/a[@class='last']")
//			.discovery();
		
		// 千千小说
		// https://www.qqxsw.co/toptime/
		new DownloadBookList("https://www.qqxsw.co/toptime/", 1, ".html")
			.setBookListCharset("GBK")
			.setShowDebug(false)
			.setBookNodePath("//div[@id='alist']//div[@id='alistbox']")
			.setBookNamePath(".//div[@class='title']/h2/a")
			.setBookUrlPath(".//div[@class='title']/h2/a/@href")
			.setTotalPagePath("//div[@class='pagelink']/a[@class='last']/@href")
			.setTotalPageExpress("\\d+")
			.discovery(null);
	}
}