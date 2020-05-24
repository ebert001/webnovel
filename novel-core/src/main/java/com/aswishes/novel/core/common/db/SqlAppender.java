package com.aswishes.novel.core.common.db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aswishes.novel.core.exception.RException;

public class SqlAppender {
	private static final Logger logger = LoggerFactory.getLogger(SqlAppender.class);
	private static final Pattern pattern = Pattern.compile(":[A-Za-z0-9_]+");
	private StringBuilder sql = new StringBuilder(512);
	private LinkedHashMap<String, Object> paramMap = new LinkedHashMap<String, Object>();
	private List<Object> paramList = new ArrayList<Object>();
	private boolean appendWhiteSpace = true;
	private boolean namedModel = false;
	private boolean insert = false;
	
	private SqlAppender() {
	}
	
	public static SqlAppender create() {
		return new SqlAppender();
	}
	
	public static SqlAppender namedModel() {
		SqlAppender appender = new SqlAppender();
		appender.namedModel = true;
		return appender;
	}
	
	public static SqlAppender create(String sqlPhrase) {
		return new SqlAppender().append(sqlPhrase);
	}
	
	public static SqlAppender create(String sqlPhrase, Object...values) {
		return create(true, sqlPhrase, values);
	}

	public static SqlAppender create(boolean condition, String sqlPhrase, Object...values) {
		return new SqlAppender().append(condition, sqlPhrase, values);
	}

	public SqlAppender append(boolean condition, Object sqlPhrase) {
		if (!condition) {
			return this;
		}
		sql.append(sqlPhrase);
		appendWhiteSpace();
		return this;
	}
	
	public SqlAppender append(Object sqlPhrase) {
		return append(true, sqlPhrase);
	}
	
	public SqlAppender append(boolean condition, String sqlPhrase) {
		if (!condition) {
			return this;
		}
		sql.append(sqlPhrase);
		appendWhiteSpace();
		return this;
	}
	
	public SqlAppender append(String sqlPhrase) {
		return append(true, sqlPhrase);
	}
	
	
	//-----------------------------------------------------------------------------------------------
	// placeholders
	//-----------------------------------------------------------------------------------------------
	public SqlAppender append(boolean condition, String sqlPhrase, Object value) {
		if (!condition) {
			return this;
		}
		sql.append(sqlPhrase);
		appendWhiteSpace();
		if (namedModel) {
			Matcher matcher = pattern.matcher(sqlPhrase);
			if (matcher.find()) {
		    	String key = matcher.group().substring(1);
				paramMap.put(key, value);
			} else {
				throw new RException("Key not found in sql phrase.");
			}
		} else {
			paramList.add(value);
		}
		return this;
	}

	public SqlAppender append(String sqlPhrase, Object value) {
		return append(true, sqlPhrase, value);
	}
	
	public SqlAppender appendIfNotNull(String sqlPhrase, Object value) {
		return append(isNotNull(value), sqlPhrase, value);
	}
	
	public SqlAppender appendIfNotEmpty(String sqlPhrase, Object value) {
		return append(isNotEmpty(value), sqlPhrase, value);
	}
	
	public SqlAppender appendIfNotBlank(String sqlPhrase, Object value) {
		return append(isNotBlank(value), sqlPhrase, value);
	}

	public SqlAppender append(boolean condition, String sqlPhrase, Object...values) {
		if (!condition) {
			return this;
		}
		sql.append(sqlPhrase);
		appendWhiteSpace();
		if (namedModel) {
			List<String> keyList = new ArrayList<String>();
			Matcher matcher = pattern.matcher(sqlPhrase);
	    	while (matcher.find()) {
	    		keyList.add(matcher.group().substring(1));
	    	}
			if (keyList.size() != values.length) {
				throw new RException("Key count[" + keyList.size() + "] and value count[" + values.length + "] is inequality.");
			}
			for (int i = 0; i < keyList.size(); i++) {
				paramMap.put(keyList.get(i), values[i]);
			}
		} else {
			paramList.addAll(Arrays.asList(values));
		}
		return this;
	}
	
	public SqlAppender append(String sqlPhrase, Object...values) {
		return append(true, sqlPhrase, values);
	}
	
	/**
	 * sql "in" condition. Parameter buffered by list.
	 * Usage: appendIn(true, "and owner_id in ", list, true);
	 * @param condition true or false.
	 * @param sqlPhrase Example: "and owner_id in ". The kit will complete the  placeholders. The result maybe "and owner_id in (?,?,?)"
	 * @param list The caller should be promise that the list has values.
	 * @return
	 */
	public SqlAppender appendIn(boolean condition, String sqlPhrase, List<Object> list) {
		if (!condition) {
			return this;
		}
		if (namedModel) {
			throw new UnsupportedOperationException("Named model cant call this method.");
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(",?");
		}
		if (sb.length() > 1) {
			sb.deleteCharAt(0);
		}
		sql.append(sqlPhrase).append(" (").append(sb.toString()).append(") ");
		appendWhiteSpace();
		paramList.addAll(list);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public SqlAppender appendIn(boolean condition, String sqlPhrase, Object listOrArray) {
		if (!condition) {
			return this;
		}
		if (listOrArray instanceof List) {
			return appendIn(condition, sqlPhrase, (List<Object>) listOrArray);
		} else if (listOrArray.getClass().isArray()) {
			Object[] arr = (Object[]) listOrArray;
			return appendIn(condition, sqlPhrase, Arrays.asList(arr));
		}
		return this;
	}
	
	public SqlAppender appendIn(boolean condition, String sqlPhrase, Object[] arr) {
		return appendIn(condition, sqlPhrase, Arrays.asList(arr));
	}
	
	
	public SqlAppender appendIn(String sqlPhrase, Object[] arr) {
		return appendIn(true, sqlPhrase, arr);
	}
	
	/**
	 * @see #appendIn(boolean, String, List)
	 * Usage: appendIn("and owner_id in ", list, true);
	 */
	public SqlAppender appendIn(String sqlPhrase, List<? extends Object> list) {
		return appendIn(true, sqlPhrase, list);
	}
	
	/**
	 * Usage: appendIn("and owner_id in ", list);
	 */
	public SqlAppender appendIn(String sqlPhrase, Object obj) {
		return appendIn(true, sqlPhrase, obj);
	}
	
	/**
	 * owner_id like '%value%'
	 */
	public SqlAppender appendLike(boolean condition, String sqlPhrase, Object value) {
		if (!condition) {
			return this;
		}
		sql.append(sqlPhrase);
		appendWhiteSpace();
		if (namedModel) {
			Matcher matcher = pattern.matcher(sqlPhrase);
			if (matcher.find()) {
		    	String key = matcher.group().substring(1);
		    	paramMap.put(key, "%" + value + "%");
			} else {
				throw new RException("Key not found in sql phrase.");
			}
		} else {
			paramList.add("%" + value + "%");
		}
		return this;
	}
	
	public SqlAppender appendLike(String sqlPhrase, Object value) {
		return appendLike(true, sqlPhrase, value);
	}
	
	public SqlAppender appendLikeIfNotNull(String sqlPhrase, Object value) {
		return appendLike(isNotNull(value), sqlPhrase, value);
	}
	
	public SqlAppender appendLikeIfNotEmpty(String sqlPhrase, Object value) {
		return appendLike(isNotEmpty(value), sqlPhrase, value);
	}
	
	public SqlAppender appendLikeIfNotBlank(String sqlPhrase, Object value) {
		return appendLike(isNotBlank(value), sqlPhrase, value);
	}
	
	/**
	 * owner_id like '%value'
	 */
	public SqlAppender appendLikeLeft(boolean condition, String sqlPhrase, Object value) {
		if (!condition) {
			return this;
		}
		sql.append(sqlPhrase);
		appendWhiteSpace();
		if (namedModel) {
			Matcher matcher = pattern.matcher(sqlPhrase);
			if (matcher.find()) {
		    	String key = matcher.group().substring(1);
		    	paramMap.put(key, "%" + value);
			} else {
				throw new RException("Key not found in sql phrase.");
			}
		} else {
			paramList.add("%" + value);
		}
		return this;
	}
	
	public SqlAppender appendLikeLeft(String sqlPhrase, Object value) {
		return appendLikeLeft(true, sqlPhrase, value);
	}
	
	public SqlAppender appendLikeLeftIfNotNull(String sqlPhrase, Object value) {
		return appendLikeLeft(isNotNull(value), sqlPhrase, value);
	}
	
	public SqlAppender appendLikeLeftIfNotEmpty(String sqlPhrase, Object value) {
		return appendLikeLeft(isNotEmpty(value), sqlPhrase, value);
	}
	
	public SqlAppender appendLikeLeftIfNotBlank(String sqlPhrase, Object value) {
		return appendLikeLeft(isNotBlank(value), sqlPhrase, value);
	}
	
	public SqlAppender appendLikeRight(boolean condition, String sqlPhrase, String value) {
		if (!condition) {
			return this;
		}
		sql.append(sqlPhrase);
		appendWhiteSpace();
		if (namedModel) {
			Matcher matcher = pattern.matcher(sqlPhrase);
			if (matcher.find()) {
		    	String key = matcher.group().substring(1);
		    	paramMap.put(key, value + "%");
			} else {
				throw new RException("Key not found in sql phrase.");
			}
		} else  {
			paramList.add(value + "%");
		}
		return this;
	}
	
	/**
	 * owner_id like 'value%'
	 */
	public SqlAppender appendLikeRight(String sqlPhrase, String value) {
		return appendLikeRight(true, sqlPhrase, value);
	}
	
	public SqlAppender appendLikeRightIfNotNull(String sqlPhrase, String value) {
		return appendLikeRight(isNotNull(value), sqlPhrase, value);
	}
	
	public SqlAppender appendLikeRightIfNotEmpty(String sqlPhrase, String value) {
		return appendLikeRight(isNotEmpty(value), sqlPhrase, value);
	}
	
	public SqlAppender appendLikeRightIfNotBlank(String sqlPhrase, String value) {
		return appendLikeRight(isNotBlank(value), sqlPhrase, value);
	}
	
	/**
	 * 用法: <br>
	 * SqlAppender appender = SqlAppender.create("insert into m_user ").appendInsert("name", name); <br>
	 * String sql = appender.getSql(); <br>
	 * Object[] arr = appender.getParamArray(); <br>
	 */
	public SqlAppender appendInsert(String name, Object value) {
		insert = true;
		paramMap.put(name, value);
		return this;
	}
	
	public SqlAppender setAppendWhiteSpace(boolean appendWhiteSpace) {
		this.appendWhiteSpace = appendWhiteSpace;
		return this;
	}
	
	public SqlAppender duplicate() {
		SqlAppender newBean = new SqlAppender();
		newBean.appendWhiteSpace = this.appendWhiteSpace;
		newBean.insert = this.insert;
		newBean.namedModel = this.namedModel;
		newBean.sql.append(this.sql);
		newBean.paramList.addAll(this.paramList);
		newBean.paramMap.putAll(this.paramMap);
		return newBean;
	}
	
	//-----------------------------------------------------------------------------------------------
	// others
	//-----------------------------------------------------------------------------------------------
	private void appendWhiteSpace() {
		if (appendWhiteSpace) {
			sql.append(" ");
		}
	}
	
	public boolean isNamedModel() {
		return namedModel;
	}
	
	public String getSql() {
		return getSql(false);
	}
	
	public String getSql(boolean showSql) {
		String str = null;
		if (insert) {
			String names = join(paramMap.keySet(), ",");
			String holders = repeat("?", ",", paramMap.size());
			StringBuilder sb = new StringBuilder(sql);
			sb.append("(").append(names).append(") values (").append(holders).append(") ");
			str = sb.toString();
		} else {
			str = sql.toString();
		}
		if (showSql) {
			logger.debug("Sql: {}", str);
		}
		return str;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public List<Object> getParamList() {
		return paramList;
	}

	public Object[] getParamArray() {
		if (insert) {
			return paramMap.values().toArray();
		}
		return paramList.toArray();
	}
	
	public static boolean isNotNull(Object value) {
		return value != null;
	}
	
	public static boolean isNotEmpty(Object value) {
		return value != null && String.valueOf(value).length() > 0;
	}
	
	public static boolean isNotBlank(Object value) {
		return value != null && String.valueOf(value).trim().length() > 0;
	}
	
	public static String join(Iterable<?> iterable, String separator) {
        if (iterable == null) {
            return null;
        }
        Iterator<?> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return "";
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return Objects.toString(first, "");
        }
        StringBuilder buf = new StringBuilder(256);
        if (first != null) {
            buf.append(first);
        }
        if (separator == null) {
        	separator = "";
        }
        while (iterator.hasNext()) {
        	buf.append(separator);
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }
	
	public static String repeat(String content, String seperator, int repeat) {
		if (content == null) {
			return null;
		}
		if (seperator == null) {
			seperator = "";
		}
		StringBuilder sb = new StringBuilder(content);
		for (int i = 0; i < repeat - 1; i++) {
			sb.append(seperator).append(content);
		}
		return sb.toString();
	}
}

