package com.aswishes.wn.spider;

import java.io.StringReader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.aswishes.wn.exception.WnException;

public class XmlTools {

	public static Document makeDocument(String content) {
		SAXReader saxReader = new SAXReader();  
		try {
			return saxReader.read(new StringReader(content));
		} catch (DocumentException e) {
			throw new WnException(e);
		}
	}
}
