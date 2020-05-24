package com.aswishes.novel.core.common.db;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Insert {
	private StringBuilder sql = new StringBuilder("insert into ");
	public static Insert table(String tableName) {
		return new Insert().tableName(tableName);
	}
	private Insert tableName(String tableName) {
		sql.append(tableName);
		return this;
	}
	public String columns(List<String> columns) {
		sql.append("(");
		String names = StringUtils.join(columns, ",");
		String values = StringUtils.repeat("?", ", ", columns.size());
		sql.append(names);
		sql.append(") values (");
		sql.append(values);
		sql.append(")");
		return sql.toString();
	}
	/**
	 * 指定insert语句需要插入的字段名称列表
	 * @param columns 字段名称列表．如：["name", "age", "birthday"]
	 * @return 最终sql语句
	 */
	public String columns(String... columns) {
		return columns(Arrays.asList(columns));
	}
	/**
	 * 指定insert语句需要插入的字段名称列表
	 * @param phrase 字段名称列表．如： "name,age,birthday"
	 * @return 最终sql语句
	 */
	public String columns(String phrase) {
		String[] ss = phrase.split(",");
		return columns(ss);
	}
}
