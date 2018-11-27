package com.aswishes.wn.common;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.security.MessageDigest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件操作工具类
 * @author lizhou
 *
 */
public class FileUtil {
	
	public static void main(String[] args) {
		delAllFile("D:\\beapp_runtime\\mail\\tmp");
	}
	
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); //删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); //删除空文件夹
	    } catch (Exception e) {
	    	e.printStackTrace(); 
	    }
	}
	
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);//再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	
	public static void mkDir(String path) {
		String folderName = path.substring(0, path.lastIndexOf("/") + 1);
		File folder = new File(folderName);
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}

	public static byte[] readInputStreamToByte(InputStream in) throws IOException {
		byte[] bytes = new byte[0];
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		byte[] cache = new byte[1024 * 4];
		int read = -1;
		while ((read = in.read(cache)) != -1) {
			out.write(cache, 0, read);
		}
		bytes = out.toByteArray();
		out.close();
		return bytes;
	}
	
	public static byte[] readFileToByte(String path) throws IOException {
		return FileUtils.readFileToByteArray(new File(path));
	}
	
	public static void writeByteArrayToFile(String path, byte[] data) throws IOException {
		mkDir(path);
		FileUtils.writeByteArrayToFile(new File(path), data);
	}
	
	public static String getHash(String fileName, String hashType) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(hashType);
			InputStream inStream = new FileInputStream(fileName);
			byte[] b = new byte[1024 * 4];
			int len = -1;
			while ((len = inStream.read(b)) != -1) {
				md.update(b, 0, len);
			}
			inStream.close();
		} catch (Exception e) {
			log.error("calculate file error:", e);
		}
		return AppUtil.bytes2Hex(md.digest());
	}
	
	public static String getHash2(String fileName, String hashType) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(hashType);
			File file = new File(fileName);
			FileInputStream inStream = new FileInputStream(file);
			FileChannel fc = inStream.getChannel();
			MappedByteBuffer mbb = fc.map(MapMode.READ_ONLY, 0, file.length());
			md.update(mbb);
			inStream.close();
		} catch (Exception e) {
			log.error("calculate file error:", e);
		}
		return AppUtil.bytes2Hex(md.digest());
	}
	
	private static final Logger log = LoggerFactory.getLogger(FileUtil.class);
}
