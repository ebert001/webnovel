package com.aswishes.novel.spider.looper;

import java.io.StringReader;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aswishes.novel.common.exception.ServiceException;

public class HtmlTools {
	private static final Logger logger = LoggerFactory.getLogger(HtmlTools.class);

	public static Document makeDocument(String content) {
		SAXReader saxReader = new SAXReader();  
		try {
			return saxReader.read(new StringReader(content));
		} catch (DocumentException e) {
			throw new ServiceException(e);
		}
	}
	
	public static String html2Xml(String content) {
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties props = cleaner.getProperties();
		TagNode node = cleaner.clean(content);
		node.removeAttribute("xmlns"); // 否则会影响dom4j使用xpath取值
		String newContent = new PrettyXmlSerializer(props).getAsString(node);
		return newContent;
	}
	
	public static Document html2Document(String html, boolean showDebug) {
		String xml = html2Xml(html);
		if (showDebug) {
			logger.debug("XML: \n{}", xml);
		}
		Document doc = HtmlTools.makeDocument(xml);
		return doc;
	}
	
	public static List<Node> findFromHtml(String html, String xpath, boolean showDebug) {
		Document doc = html2Document(html, showDebug);
		List<Node> nodes = doc.selectNodes(xpath);
		return nodes;
	}
	
	public static List<Node> findFromXml(String xml, String xpath) {
		Document doc = HtmlTools.makeDocument(xml);
		List<Node> nodes = doc.selectNodes(xpath);
		return nodes;
	}
}
