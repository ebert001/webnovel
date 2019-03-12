package com.aswishes.wn.spider;

import org.junit.Test;

import com.aswishes.wn.spider.looper.PickCatalog;

public class DownloadBookTest {

	@Test
	public void testDiscovery() throws Exception {
//		new DownloadBook("https://m.lingyu.org/95/95050/")
//			.setChapterNodePath("//ul/li/a")
//			.setCatalogCharset("GBK")
//			.setChapterUrlNode("href")
//			.setContentCharset("GBK")
//			.discovery();

		// https://www.lingyu.org/wjsw/21/21995/25250300.html
		PickCatalog bean = new PickCatalog("https://www.lingyu.org/wjsw/21/21995/");
		bean.setShowDebug(true);
		bean.setCatalogChapterNodePath("//div[@class='ml_list']/ul/li/a");
		bean.setCatalogCharset("GBK");
		bean.setCatalogChapterUrlPath("href");
		bean.setChapterCharset("GBK");
		bean.setChapterNodePath("//div[@class='novelcontent']/p/self::*");
		bean.setWeeds("./www.lingyu.org", "", "./领域文学", "", " ♂领♂域♂文♂学♂*♂www.li♂ng♂yu.or♂g", "");
		bean.discovery();
		
//		new DownloadBook("http://www.77xsw.la/book/10647/")
//			.setShowDebug(false)
//			.setChapterNodePath("//div[@id='list-chapterAll']/dl/dd/a")
//			.setCatalogCharset("GBK")
//			.setChapterUrlNode("href")
//			.setContentCharset("GBK")
//			.setContentNodePath("//div[@id='htmlContent']/self::*")
//			.setReplaceKeywords("一秒记住【千千小说网 www.77xsw.la】，更新快，无弹窗，免费读！", "")
//			.discovery();
	}
}
