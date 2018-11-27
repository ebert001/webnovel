package com.aswishes.wn.common.pdf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * pdf文件读取。注意：只能读取pdf中的文字；如果文字在图片上，则不能读取。
 * @author lizhou
 *
 */
public class PDFReader {
	
	public static PDFReader load(String pathname) {
		return new PDFReader(pathname);
	}
	
	public static PDFReader load(InputStream inStream) {
		return new PDFReader(inStream);
	}
	
	
	public PDFReader(String pathname) {
		this.pathname = pathname;
		try {
			doc = getDoc();
		} catch (IOException e) {
			log.error("加载输入文件错误", e);
		}
	}
	
	public PDFReader(InputStream inStream) {
		this.inStream = inStream;
		try {
			doc = getDoc();
		} catch (IOException e) {
			log.error("加载输入流错误", e);
		}
	}
	
	public String readText(int startPage, int endPage) {
		try {
			PDFTextStripper stripper = new PDFTextStripper();
			stripper.setStartPage(startPage);
			stripper.setEndPage(endPage);
			
			return stripper.getText(doc);
		} catch (IOException e) {
			log.error("pdf加载或读取错误", e);
		}
		return null;
	}
	
	public String getSubject() {
		PDDocumentInformation docInfo = doc.getDocumentInformation();
		return docInfo.getSubject();
	}
	
	/**
	 * 获取文档的总页数
	 * @return 总页数
	 */
	public int getTPage() {
		return doc.getNumberOfPages();
	}
	
	/**
	 * 获取pdf文档，优先取用输入流
	 * @return pdf文档对象
	 * @throws IOException 如果加载pdf对象失败，抛出此异常
	 */
	private PDDocument getDoc() throws IOException {
		if (null == inStream) {
			return PDDocument.load(new File(pathname));
		}
		return PDDocument.load(inStream);
	}
	
	private PDDocument doc = null;
	
	private String pathname = null;
	
	private InputStream inStream = null;
	
	private static final Logger log = LoggerFactory.getLogger(PDFReader.class);
}
