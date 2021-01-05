package com.aswishes.novel.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 应用程序工具类
 */
public class Helper {
	
	/**
	 * Base64编码
	 * @param str 待编码串
	 * @return 编码后的串
	 */
	public static String base64Encode(String str) {
		if (null == str) {
			return null;
		}
		return new String(Base64.encodeBase64(str.getBytes()));
	}
	
	/**
	 * Base64解码
	 * @param str 待解码串
	 * @return 解码后的串
	 */
	public static String base64Decode(String str) {
		if (null == str) {
			return null;
		}
		return new String(Base64.decodeBase64(str.getBytes()));
	}
	
	/**
	 * 字符转字节
	 * @param c 字符
	 * @return 字节
	 */
	public static byte char2Byte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	/**
	 * 二进制数组转十六进制字符串
	 * @param bs 二进制数组
	 * @return 十六进制串
	 */
	public static String bytes2Hex(byte[] bs) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bs.length; i++) {
			// byte转int，与操作，高位清0
			String hex = Integer.toHexString(bs[i] & 0xFF);
			// 一个字节转换成十六进制以后应该有2为长度。
			// 转换以后至少有1位，如果少1位，则在高位补0
			if (hex.length() < 2) {
				sb.append(0);
			}
			sb.append(hex);
		}
		return sb.toString().toUpperCase();
	}
	
	/**
	 * 十六进制串转二进制数组
	 * @param hex 十六进制串
	 * @return 二进制数组
	 */
	public static byte[] hex2Bytes(String hex) {
		hex = hex.toUpperCase();
		// 取字节长度
		int length = hex.length() / 2;
		char[] cs = hex.toCharArray();
		byte[] bs = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			// 第一个字符左移4位，为第二个字符让位。或操作，连接运算
			bs[i] = (byte) (char2Byte(cs[pos]) << 4 | char2Byte(cs[pos + 1]));
		}
		return bs;
	}
	
	/**
	 * 字符串转十六进制串
	 * @param str 字符串
	 * @return 十六进制串
	 */
	public static String string2Hex(String str) {
		if (null == str) {
			return null;
		}
		return bytes2Hex(str.getBytes());
	}
	
	/**
	 * 十六进制串转字符串
	 * @param hex 十六进制串
	 * @return 字符串
	 */
	public static String hex2String(String hex) {
		if (null == hex) {
			return null;
		}
		return new String(hex2Bytes(hex));
	}
	
	public static String toJsonString(Object obj) {
		return JSON.toJSONString(obj);
	}
	
	/**
	 * web 应用程序内跳转
	 * @param request
	 * @param response
	 * @param path
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void forward(HttpServletRequest request, 
			HttpServletResponse response, String path) throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, String> reuqestMap(HttpServletRequest request) {
		Map<String, String[]> pmap = request.getParameterMap();
		if (pmap == null || pmap.size() < 1) {
			return new HashMap<String, String>();
		}
		Map<String, String> rmap = new HashMap<String, String>();
		for (Map.Entry<String, String[]> entry : pmap.entrySet()) {
			String[] v = entry.getValue();
			if (v == null || v.length < 1) {
				continue;
			}
			String key = entry.getKey();
			if (v.length == 1) {
				rmap.put(key, v[0]);
			} else {
				rmap.put(key, StringUtils.join(v));
			}
		}
		return rmap;
	}
	
	/**
	 * md5加密
	 * @param str 源字符串
	 * @return 加密后的串
	 */
	public static String md5codec(String str) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}
		byte[] ba = md.digest();
		
		StringBuilder sb = new StringBuilder();  
        for (int i = 0; i < ba.length; i++) {              
            if (Integer.toHexString(0xFF & ba[i]).length() == 1) {
                sb.append("0").append(Integer.toHexString(0xFF & ba[i]));  
            } else {
                sb.append(Integer.toHexString(0xFF & ba[i]));
            }
        }  
        return sb.toString();  
	}
	
	/**
	 * uuid生成器
	 * @return 32位长的唯一字符串
	 */
	public static String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 获取指定字符串的驼峰命名法。注意：
	 * 如果没有_，直接返回字符串；
	 * 如果只有首字母为_，去掉_，变小写返回；
	 * 如果尾字母为_，直接去掉；
	 * 字符中间包含_，去掉_，紧跟其后的字母变大写。
	 * @param str 可以包含有下划线的字符串
	 * @return 驼峰形式的字符串。
	 */
	public static String getHump(String str) {
		if (null == str || "".equals(str) || -1 == str.indexOf("_")) {
			return str;
		}
		if (str.startsWith("_")) {
			str = str.replaceFirst("_", "");
		}
		str = str.toLowerCase();
		char[] cs = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for (int i = 0; i < cs.length; i++) {
			char c = cs[i];
			if ('_' == c) {
				flag = true;
				continue;
			}
			if (flag) {
				c = Character.toUpperCase(c);
				flag = false;
			}
			sb.append(c);
		}
		return sb.toString();
	}
	
	/**
	 * 字符串首字母大写
	 * @param str 输入字符串
	 * @return 大写首字母后的字符串
	 * 
	 */
	public static String upperCaseFirst(String str) {
		char c = str.charAt(0);
		return str.replaceFirst(c + "", Character.toUpperCase(c) + "");
	}
	
	/**
	 * 字符串首字母小写
	 * @param str 输入字符串
	 * @return 小写首字母后的字符串
	 */
	public static String lowerCaseFirst(String str) {
		char c = str.charAt(0);
		return str.replaceFirst(c + "", Character.toLowerCase(c) + "");
	}
	
	/**
	 * <code>java.lang.Field</code>数组转换为以Field的name为键，Field本身为值得map
	 * @param fields Field[]
	 * @return Map&lt;String, Field&gt;
	 */
	public static Map<String, Field> fieldMap(Field[] fields) {
		Map<String, Field> m = new HashMap<String, Field>();
		for (Field f : fields) {
			m.put(f.getName(), f);
		}
		return m;
	}
	
	/**
	 * Map&lt;String, Object&gt;转换成 bean 对象<br>
	 * 说明：转换是根据bean的属性来进行的。 如果map的键和bean的属性相同，就把map对应的值赋给bean的属性。 
	 * @param map 存储有值得map
	 * @param bean 参与赋值操作的bean
	 */
	public static <T> T mapToBean(Map<String, Object> map, T bean) {
		if (map == null || map.size() < 1 || bean == null) {
			return bean;
		}
		Class<?> bclass = bean.getClass();
		Map<String, Field> m = fieldMap(bclass.getDeclaredFields());
		Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Object> entry = iter.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			try {
				Field f = m.get(key);
				if (f != null) {
					f.setAccessible(true);
					f.set(bean, convertType(value, f.getType()));
				}
			} catch (Exception e) {
				logger.error("map to bean error. the key is [" + key + "]", e);
			}
		}
		return bean;
	}
	
	public static Object convertType(Object value, Class<?> type) {
		logger.debug("type:" + type.getName());
		if (value == null || type == null) {
			return null;
		}
		if (type.isInstance(value)) { // 如果是同一类型，就不用进行类型处理
			return value;
		}
		// 以下只处理基本类型，字符串和日期
		if (type.isInstance(Byte.TYPE)) {
			return (Byte) value;
		} else if (type.isInstance(Short.TYPE)) {
			return (Short) value;
		} else if (type.isInstance(Integer.TYPE)) {
			return (Integer) value;
		} else if (type.isInstance(Long.TYPE)) {
			return (Long) value;
		} else if (type.isInstance(Character.TYPE)) {
			return (Character) value;
		} else if (type.isInstance(Float.TYPE)) {
			return (Float) value;
		} else if (type.isInstance(Double.TYPE)) {
			return (Double) value;
		} else if (type.isInstance(String.class)) {
			return (String) value;
		} else if (type.isInstance(Date.class)) {
			// TODO 日期的处理需要再斟酌
			return DateUtil.parseDate((String) value, "yyyy-MM-dd ");
		} else {
			return value;
		}
	}
	
	/**
	 * 计算分页数量。页数的最小值为 1。
	 * @param count 总数量
	 * @param perCount 每页数量
	 * @return 页数
	 */
	public static int calPageCount(int count, int perCount) {
		return ((count != 0 && count % perCount == 0) ? (count / perCount) : ((count / perCount) + 1));
	}
	
	/**
	 * 计算分页起始位置
	 * @param startPage 开始页
	 * @param perCount 每页数量
	 * @return 起始位置
	 */
	public static int calStartNo(int startPage, int perCount) {
		return (startPage - 1) * perCount;
	}
	
	public static Object[] toArray(Object...args) {
		Object[] rr = new Object[args.length];
		for (int i = 0; i < args.length; i++) {
			rr[i] = args[i];
		}
		return rr;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(Helper.class);
}
