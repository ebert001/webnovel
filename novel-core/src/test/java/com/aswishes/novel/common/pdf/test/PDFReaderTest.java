package com.aswishes.novel.common.pdf.test;

import org.junit.Test;

import com.aswishes.novel.core.common.pdf.PDFReader;

import junit.framework.TestCase;

public class PDFReaderTest extends TestCase {
	
	private PDFReader reader = null;
	
	protected void setUp() throws Exception {
		String name = "src/test/resources/pkcs-11v2-11r1.pdf";
		reader = PDFReader.load(name);
	}

	@Test
	public void testReadText() {
		String result = reader.readText(13, 14);
		assertEquals(374, reader.getTPage());
		assertTrue(result.contains("INTRODUCTION  xiii"));
	}
	
}
