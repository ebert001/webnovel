package com.aswishes.novel.common;

import java.text.MessageFormat;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum NovelStatus {
	SUCCESS(1, "OK", "lbl.ok"),
	USER_NOT_LOGIN(2, "User dont login"),
	WEBSITE_EXISTS(3, "Website exists"),
	WEBSITE_BOOK_EXISTS(4, "Website book exists"),
	WEBSITE_NOT_EXISTS(5, "Website do not exist"),
	BOOK_LIST_CACHE_FULL(6, "Book list cache is full"),
	BOOK_CACHE_FULL(7, "Book cache is full"),
	BOOK_CHAPTER_EXISTS(8, "Book chapter exists"),
	BOOK_EXISTS_IN_CACHE(9, "Book exists in cache"),
	WEBSITE_EXISTS_IN_CACHE(10, "Website exists in cache"),
	BOOK_LOST(11, "Book lost."),
	BOOK_IS_FAVORiTE(12, "Book is favorite."),
	;

	private int code;
	/** 开发可见的日志消息 */
	private String logMessage;
	/** 用户可见的提示消息 */
	private String tipMessage;
	/** 用户可见提示消息是否是国际化标签 */
	private boolean i18n = false;

	private NovelStatus(int code, String logMessage) {
		this.code = code;
		this.logMessage = logMessage;
	}
	private NovelStatus(int code, String logMessage, String tipMessage) {
		this(code, logMessage);
		this.tipMessage = tipMessage;
		this.i18n = true;
	}
	private NovelStatus(int code, String logMessage, String tipMessage, boolean i18n) {
		this(code, logMessage);
		this.tipMessage = tipMessage;
		this.i18n = i18n;
	}

	/**
	 * @return 状态码
	 */
	public int getCode() {
		return this.code;
	}
	/**
	 * @return 日志消息
	 */
	public String getLogMessage(Object...args) {
		if (ArrayUtils.isEmpty(args)) {
			return this.logMessage;
		}
		return MessageFormat.format(this.logMessage, args);
	}

	/**
	 * @return 提示消息。如果tip message为 null，将返回 "日志消息"，请注意处理.
	 */
	public String getTipMessage(Object... args) {
		if (this.tipMessage == null) {
			if (ArrayUtils.isNotEmpty(args)) {
				return getLogMessage(args);
			}
			return this.logMessage;
		}
		if (i18n) {
			logger.warn("Unsupport i18n");
		}
		return MessageFormat.format(this.tipMessage, args);
	}
	
	private static final Logger logger = LoggerFactory.getLogger(NovelStatus.class);
}
