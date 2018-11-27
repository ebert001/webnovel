package com.aswishes.wn.common.checkcode.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

import com.aswishes.wn.common.checkcode.CheckCodeGenerator;

public class CheckCodeGeneratorTest {

	@Test
	public void testGenerate() {
		OutputStream outStream = null;
		try {
			outStream = new FileOutputStream(new File("d:/aa.jpg"));
			String checkcode = CheckCodeGenerator.generate(outStream, CheckCodeGenerator.SIMPLE);
			System.out.println(checkcode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
