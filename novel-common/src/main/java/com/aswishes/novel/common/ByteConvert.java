package com.aswishes.novel.common;

final public class ByteConvert {
	final static String lineSeperate = "\r\n";
	final public static int DEFAULT_BUFFERLENGTH = 1024 * 3;
	final public static int DEFAULT_TABLELENGTH = 256;
	// update start by hans_j at 2004-8-15 21:08:49
	// private static StringBuffer sBuf;
	// update end by hans_j at 2004-8-15
	private static String[] convertTable;

	static {
		convertTable = new String[DEFAULT_TABLELENGTH];
		// update start by hans_j at 2004-8-15 21:09:04
		// sBuf = new StringBuffer(DEFAULT_BUFFERLENGTH);
		// update end by hans_j at 2004-8-15

		int i = 0;
		for (i = 0; i < 16; i++) {
			convertTable[i] = "0" + Integer.toHexString(i) + " ";
		}
		for (; i < 256; i++) {
			convertTable[i] = Integer.toHexString(i) + " ";
		}
	}

	/**
	 * convert two byte to int, in fact return int for represent unsigned short.
	 * 
	 * @param convertByte byte stream
	 * @return int
	 * @exception exceptions No exceptions thrown
	 */
	public static int byte2int2(byte[] convertByte) {
		return byte2int2(convertByte, 0, true);
	}

	// add by jhq at 2004-6-15 for bigIndian
	public static int byte2int2(byte[] convertByte, boolean bigIndian) {
		return byte2int2(convertByte, 0, bigIndian);
	}

	// add end at 2004-6-15
	/**
	 * convert two byte to int, in fact return int for represent unsigned short.
	 * 
	 * @param convertByte byte stream
	 * @param offset offset of byte stream to be converted
	 * @return int
	 * @exception exceptions No exceptions thrown
	 */
	public static int byte2int2(byte[] convertByte, int offset) {
		// update by jhq at 2004-6-15
		// int value=0;
		// int byte0,byte1;
		//        
		// byte0=convertByte[0+offset]<0?convertByte[0+offset]+256:convertByte[0+offset];
		// byte1=convertByte[1+offset]<0?convertByte[1+offset]+256:convertByte[1+offset];
		//
		// value=byte1*256+byte0;
		// return value;
		return byte2int2(convertByte, offset, true);
		// update end at 2004-6-15
	}

	// add by jhq at 2004-6-15
	public static int byte2int2(byte[] convertByte, int offset,
			boolean bigIndian) {
		int value = 0;
		int byte0, byte1;

		byte0 = convertByte[0 + offset] < 0 ? convertByte[0 + offset] + 256
				: convertByte[0 + offset];
		byte1 = convertByte[1 + offset] < 0 ? convertByte[1 + offset] + 256
				: convertByte[1 + offset];
		// update start by Hans_j at 2005-6-8 9:52:19
		// if (bigIndian)
		if (!bigIndian)
			// update end by Hans_j at 2005-6-8
			value = byte1 * 256 + byte0;
		else
			value = byte0 * 256 + byte1;

		return value;
	}

	// add end at 2004-6-15
	public static int byte2int2sign(byte[] convertByte) {
		return byte2int2sign(convertByte, 0, true);
	}
	public static int byte2int2sign(byte[] convertByte, int offset,
			boolean bigIndian) {
		int value = 0;
		if (!bigIndian)
			value = (convertByte[1 + offset] << 8)
					| (convertByte[0 + offset] & 0xff);
		else
			value = (convertByte[0 + offset] << 8)
					| (convertByte[1 + offset] & 0xff);
		return value;
	}

	/**
	 * convert four byte to int.
	 * 
	 * @param convertByte byte stream
	 * @return int
	 * @exception exceptions No exceptions thrown
	 */
	public static int byte2int4(byte[] convertByte) {
		return byte2int4(convertByte, 0, true);
	}

	// add by jhq at 2004-6-15
	public static int byte2int4(byte[] convertByte, boolean bigIndian) {
		return byte2int4(convertByte, 0, bigIndian);
	}

	// add end 2004-6-15
	/**
	 * convert four byte to int.
	 * 
	 * @param convertByte byte stream
	 * @param offset offset of byte stream to be converted
	 * @return int
	 * @exception exceptions No exceptions thrown
	 */
	public static int byte2int4(byte[] convertByte, int offset) {
		// update by jhq at 2004-6-15
		// int value = 0;
		// int byte0, byte1, byte2, byte3;
		//
		// byte0 =
		// convertByte[0 + offset] < 0
		// ? convertByte[0 + offset] + 256
		// : convertByte[0 + offset];
		// byte1 =
		// convertByte[1 + offset] < 0
		// ? convertByte[1 + offset] + 256
		// : convertByte[1 + offset];
		// byte2 =
		// convertByte[2 + offset] < 0
		// ? convertByte[2 + offset] + 256
		// : convertByte[2 + offset];
		// byte3 =
		// convertByte[3 + offset] < 0
		// ? convertByte[3 + offset] + 256
		// : convertByte[3 + offset];
		//
		// value = (byte3 << 24) + (byte2 << 16) + (byte1 << 8) + byte0;
		// return value;
		return byte2int4(convertByte, offset, true);
		// update end at 2004-6-15
	}

	// add by jhq at 2004-6-15
	public static int byte2int4(byte[] convertByte, int offset,
			boolean bigIndian) {
		int value = 0;
		int byte0, byte1, byte2, byte3;

		byte0 = convertByte[0 + offset] < 0 ? convertByte[0 + offset] + 256
				: convertByte[0 + offset];
		byte1 = convertByte[1 + offset] < 0 ? convertByte[1 + offset] + 256
				: convertByte[1 + offset];
		byte2 = convertByte[2 + offset] < 0 ? convertByte[2 + offset] + 256
				: convertByte[2 + offset];
		byte3 = convertByte[3 + offset] < 0 ? convertByte[3 + offset] + 256
				: convertByte[3 + offset];
		// update start by Hans_j at 2005-6-8 9:54:21
		// if (bigIndian)
		if (!bigIndian)
			// update end by Hans_j at 2005-6-8
			value = (byte3 << 24) + (byte2 << 16) + (byte1 << 8) + byte0;
		else
			value = (byte0 << 24) + (byte1 << 16) + (byte2 << 8) + byte3;
		return value;
	}

	// add end at 2004-6-15
	public static int byte2int4sign(byte[] convertByte, int offset,
			boolean bigIndian) {
		int value = 0;
		if (!bigIndian)
			value = ((convertByte[3 + offset] << 24) & 0xff000000)
					| ((convertByte[2 + offset] << 16) & 0xff0000)
					| ((convertByte[1 + offset] << 8) & 0xff00)
					| (convertByte[0 + offset] & 0xff);
		else
			value = ((convertByte[0 + offset] << 24) & 0xff000000)
					| ((convertByte[1 + offset] << 16) & 0xff0000)
					| ((convertByte[2 + offset] << 8) & 0xff00)
					| (convertByte[3 + offset] & 0xff);
		return value;
	}
	
	/**
	 * convert short to two byte.
	 * 
	 * @param value int value represent unsigned short
	 * @return byte[]
	 * @exception exceptions No exceptions thrown
	 */
	public static byte[] int2byte2(int value) {
		// update by jhq at 2004-6-15
		// byte[] byteValue = new byte[2];
		//
		// byteValue[0] = (byte) (value & 0xff);
		// byteValue[1] = (byte) ((value & 0xff00) >>> 8);
		// return byteValue;
		return int2byte2(value, true);
		// update end at 2004-6-15
	}

	// add by jhq at 2004-6-15
	public static byte[] int2byte2(int value, boolean bigIndian) {
		byte[] byteValue = new byte[2];
		// update start by Hans_j at 2005-6-8 10:15:35
		// if (bigIndian) {
		if (!bigIndian) {
			// update end by Hans_j at 2005-6-8
			byteValue[0] = (byte) (value & 0xff);
			byteValue[1] = (byte) ((value & 0xff00) >>> 8);
		}
		else {
			byteValue[1] = (byte) (value & 0xff);
			byteValue[0] = (byte) ((value & 0xff00) >>> 8);
		}
		return byteValue;
	}

	// add end at 2004-6-15
	/**
	 * convert short to two byte.
	 * 
	 * @param value int value represent unsigned short
	 * @param fillByte byte stream to set
	 * @return void
	 * @exception exceptions No exceptions thrown
	 */
	public static void int2byte2(int value, byte[] fillByte) {
		// update by jhq at 2004-6-15
		// int2byte2(value, fillByte, 0);
		int2byte2(value, fillByte, 0, true);
		// update end at 2004-6-15
	}

	// add by jhq at 2004-6-15
	public static void int2byte2(int value, byte[] fillByte, boolean bigIndian) {
		int2byte2(value, fillByte, 0, bigIndian);
	}

	// add end at 2004-6-15
	/**
	 * convert short to two byte.
	 * 
	 * @param value int value represent unsigned short
	 * @param fillByte byte stream to set
	 * @param fillByte at the offset of byte stream to set
	 * @return void
	 * @exception exceptions No exceptions thrown
	 */
	public static void int2byte2(int value, byte[] fillByte, int offset) {
		// update by jhq at 2004-6-15
		// fillByte[0 + offset] = (byte) (value & 0xff);
		// fillByte[1 + offset] = (byte) ((value & 0xff00) >>> 8);
		int2byte2(value, fillByte, offset, true);
		// update end at 2004-6-15
	}

	// add by jhq at 2004-6-15
	public static void int2byte2(int value, byte[] fillByte, int offset,
			boolean bigIndian) {
		// update start by Hans_j at 2005-6-8 10:15:35
		// if (bigIndian) {
		if (!bigIndian) {
			// update end by Hans_j at 2005-6-8
			fillByte[0 + offset] = (byte) (value & 0xff);
			fillByte[1 + offset] = (byte) ((value & 0xff00) >>> 8);
		}
		else {
			fillByte[1 + offset] = (byte) (value & 0xff);
			fillByte[0 + offset] = (byte) ((value & 0xff00) >>> 8);
		}
	}

	// add at 2004-6-15
	/**
	 * convert int to four byte.
	 * 
	 * @param value int
	 * @return byte[]
	 * @exception exceptions No exceptions thrown
	 */
	public static byte[] int2byte4(int value) {
		// update by jhq at 2004-6-15
		// byte[] byteValue = new byte[4];
		//
		// byteValue[0] = (byte) (value & 0xff);
		// byteValue[1] = (byte) ((value & 0xff00) >>> 8);
		// byteValue[2] = (byte) ((value & 0xff0000) >>> 16);
		// byteValue[3] = (byte) ((value & 0xff000000) >>> 24);
		// return byteValue;
		return int2byte4(value, true);
		// update end at 2004-6-15
	}

	// add by jhq at 2004-6-15
	public static byte[] int2byte4(int value, boolean bigIndian) {
		byte[] byteValue = new byte[4];
		// update start by Hans_j at 2005-6-8 10:09:12
		// if (bigIndian) {
		if (!bigIndian) {
			// update end by Hans_j at 2005-6-8
			byteValue[0] = (byte) (value & 0xff);
			byteValue[1] = (byte) ((value & 0xff00) >>> 8);
			byteValue[2] = (byte) ((value & 0xff0000) >>> 16);
			byteValue[3] = (byte) ((value & 0xff000000) >>> 24);
		}
		else {
			byteValue[3] = (byte) (value & 0xff);
			byteValue[2] = (byte) ((value & 0xff00) >>> 8);
			byteValue[1] = (byte) ((value & 0xff0000) >>> 16);
			byteValue[0] = (byte) ((value & 0xff000000) >>> 24);
		}
		return byteValue;
	}

	// add end at 2004-6-15
	/**
	 * convertint to four byte.
	 * 
	 * @param value int value represent unsigned short
	 * @param fillByte byte stream to set
	 * @return void
	 * @exception exceptions No exceptions thrown
	 */
	public static void int2byte4(int value, byte[] fillByte) {
		// update by jhq at 2004-6-15
		// int2byte4(value, fillByte, 0);
		int2byte4(value, fillByte, 0, true);
		// update end at 2004-6-15
	}

	// add by jhq at 2004-6-15
	public static void int2byte4(int value, byte[] fillByte, boolean bigIndian) {
		int2byte4(value, fillByte, 0, bigIndian);
	}

	// add end at 2004-6-15
	/**
	 * convertint to four byte.
	 * 
	 * @param value int value represent unsigned short
	 * @param fillByte byte stream to set
	 * @param fillByte at the offset of byte stream to set
	 * @return void
	 * @exception exceptions No exceptions thrown
	 */
	public static void int2byte4(int value, byte[] fillByte, int offset) {
		// update by jhq at 2004-6-15
		// fillByte[0 + offset] = (byte) (value & 0xff);
		// fillByte[1 + offset] = (byte) ((value & 0xff00) >>> 8);
		// fillByte[2 + offset] = (byte) ((value & 0xff0000) >>> 16);
		// fillByte[3 + offset] = (byte) ((value & 0xff000000) >>> 24);
		int2byte4(value, fillByte, offset, true);
		// update end at 2004-6-15
	}

	// add by jhq at 20074-6-15
	public static void int2byte4(int value, byte[] fillByte, int offset,
			boolean bigIndian) {
		// update start by Hans_j at 2005-6-8 10:15:35
		// if (bigIndian) {
		if (!bigIndian) {
			// update end by Hans_j at 2005-6-8
			fillByte[0 + offset] = (byte) (value & 0xff);
			fillByte[1 + offset] = (byte) ((value & 0xff00) >>> 8);
			fillByte[2 + offset] = (byte) ((value & 0xff0000) >>> 16);
			fillByte[3 + offset] = (byte) ((value & 0xff000000) >>> 24);
		}
		else {
			fillByte[3 + offset] = (byte) (value & 0xff);
			fillByte[2 + offset] = (byte) ((value & 0xff00) >>> 8);
			fillByte[1 + offset] = (byte) ((value & 0xff0000) >>> 16);
			fillByte[0 + offset] = (byte) ((value & 0xff000000) >>> 24);
		}
	}

	// add end at 2004-6-15
	public static String buf2String(String info, byte[] buf) {
		// update by jhq at 2004-6-15
		// return buf2String(info, buf, 0, buf.length);
		return buf2String(info, buf, 0, buf.length, true);
		// update end at 2004-6-15
	}

	// add by jhq at 2004-6-15
	public static String buf2String(String info, byte[] buf, boolean oneLine16) {
		return buf2String(info, buf, 0, buf.length, oneLine16);
	}

	// add end at 2004-6-15
	public static String buf2String(String info, byte[] buf, int offset,
			int length) {
		// update by jhq at 2004-6-15
		// int i, index;
		//
		// sBuf.delete(0, sBuf.length());
		// sBuf.append(info);
		//
		// for (i = 0 + offset; i < length + offset; i++) {
		// if (i % 16 == 0) {
		// sBuf.append(lineSeperate);
		// } else if (i % 8 == 0) {
		// sBuf.append(" ");
		// }
		// index = buf[i] < 0 ? buf[i] + DEFAULT_TABLELENGTH : buf[i];
		// sBuf.append(convertTable[index]);
		// }
		//
		// return sBuf.toString();
		return buf2String(info, buf, offset, length, true);
		// update end at 2004-6-15
	}

	// add by jhq at 2004-6-15 for no return String
	public static String buf2String(String info, byte[] buf, int offset,
			int length, boolean oneLine16) {
		int i, index;
		// update start by hans_j at 2004-8-15 21:08:15
		// sBuf.delete(0, sBuf.length());
		// update start by hans_j at 2004-9-24 15:47:58
		// if(Config.ENABLE_MEMORY_INFO)com.shera.cldc.util.MemMonitor.printInfo("bBuf2Str");
		// update end by hans_j at 2004-9-24

		StringBuffer sBuf = new StringBuffer();
		// update end by hans_j at 2004-8-15
		sBuf.append(info);

		for (i = 0 + offset; i < length + offset; i++) {
			if (i % 16 == 0) {
				if (oneLine16) {
					sBuf.append(lineSeperate);
				}
			}
			else if (i % 8 == 0) {
				if (oneLine16) {
					sBuf.append("   ");
				}
			}
			index = buf[i] < 0 ? buf[i] + DEFAULT_TABLELENGTH : buf[i];
			sBuf.append(convertTable[index]);
		}

		// update start by hans_j at 2004-9-24 15:47:58
		// if(Config.ENABLE_MEMORY_INFO)com.shera.cldc.util.MemMonitor.printInfo("eBuf2Str");
		// update end by hans_j at 2004-9-24
		return sBuf.toString();
	}
	// add end at 2004-6-15
	public static String buf2StringCompact(byte[] buf) {
		int i, index;
		StringBuffer sBuf = new StringBuffer();
		for (i = 0 ; i < buf.length; i++) {
			index = buf[i] < 0 ? buf[i] + DEFAULT_TABLELENGTH : buf[i];
			if(index<16){
				sBuf.append("0"+Integer.toHexString(index));
			}else{
				sBuf.append(Integer.toHexString(index));
			}
		}
		return sBuf.toString();
	}
	
	public static byte[] longToByte(long v) {
	    byte[] result = new byte[8];
	    for (int i = 7; i >= 0; i--) {
	        result[i] = (byte)(v & 0xFF);
	        v >>= 8;
	    }
	    return result;
	}

	public static long byteToLong(byte[] bs) {
	    long result = 0;
	    for (int i = 0; i < 8; i++) {
	        result <<= 8;
	        result |= (bs[i] & 0xFF);
	    }
	    return result;
	}
}

