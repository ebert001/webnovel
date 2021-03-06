package com.aswishes.novel.common.checkcode.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

import com.aswishes.novel.core.common.checkcode.VerificationCodeGenerator;

public class CheckCodeGeneratorTest {

	@Test
	public void testGenerate() {
		OutputStream outStream = null;
		try {
			File outFile = new File(System.getProperty("java.io.tmpdir"), "test.png");
			outStream = new FileOutputStream(outFile);
			String checkcode = VerificationCodeGenerator.generate(outStream, VerificationCodeGenerator.SIMPLE);
			System.out.println(checkcode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
