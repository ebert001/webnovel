package com.aswishes.wn.common;

import java.text.MessageFormat;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum WnStatus {
	SUCCESS(1, "OK", "lbl.ok"),
	WEBSITE_EXISTS(2, "Website exists"),
	WEBSITE_BOOK_EXISTS(3, "Website book exists"),
	WEBSITE_NOT_EXISTS(4, "Website do not exist"),
	;

	private int code;
	/** 开发可见的日志消息 */
	private String logMessage;
	/** 用户可见的提示消息 */
	private String tipMessage;
	/** 用户可见提示消息是否是国际化标签 */
	private boolean i18n = false;

	private WnStatus(int code, String logMessage) {
		this.code = code;
		this.logMessage = logMessage;
	}
	private WnStatus(int code, String logMessage, String tipMessage) {
		this(code, logMessage);
		this.tipMessage = tipMessage;
		this.i18n = true;
	}
	private WnStatus(int code, String logMessage, String tipMessage, boolean i18n) {
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
	
	private static final Logger logger = LoggerFactory.getLogger(WnStatus.class);
}
