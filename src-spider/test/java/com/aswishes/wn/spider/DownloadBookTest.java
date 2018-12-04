package com.aswishes.wn.spider;

import org.junit.Test;

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
		new DownloadBook("https://www.lingyu.org/wjsw/21/21995/")
			.setShowDebug(true)
			.setChapterNodePath("//div[@class='ml_list']/ul/li/a")
			.setCatalogCharset("GBK")
			.setChapterUrlNode("href")
			.setContentCharset("GBK")
			.setContentNodePath("//div[@class='novelcontent']/p/self::*")
//			.setIgnoreMarkOfChapterAttribute("")
			.setReplaceKeywords("./www.lingyu.org", "", "./领域文学", "", " ♂领♂域♂文♂学♂*♂www.li♂ng♂yu.or♂g", "")
			.discovery();
	}
}
