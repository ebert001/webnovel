package com.aswishes.novel.common.pdf.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.aswishes.novel.common.pdf.PDFReader;

public class PDFReaderTest extends TestCase {
	
	private PDFReader reader = null;
	
	protected void setUp() throws Exception {
		reader = PDFReader.load("/home/lizhou/123/三国演义(上).pdf");
	}

	@Test
	public void testReadText() {
		String result = reader.readText(13, 18);
		System.out.println(reader.getTPage());
		System.out.println(result);
	}
	
	@Test
	public void testGetSubject() {
		System.out.println(reader.getSubject());
	}
}
