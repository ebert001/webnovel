package com.aswishes.novel.common.exception;

import com.aswishes.novel.common.NovelStatus;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = -6634300136490098961L;

    protected int code;
    protected NovelStatus status;
    protected Object[] logArgs;
    protected Object[] tipArgs;
	
	public ServiceException() {
	}
	
	public ServiceException(int code) {
		this.code = code;
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ServiceException(int code, String message) {
		super(message);
		this.code = code;
	}

	public ServiceException(int code, Throwable e) {
		super(e);
		this.code = code;
	}
	
	public ServiceException(int code, String message, Throwable e) {
		super(message, e);
		this.code = code;
	}
	
	public ServiceException(NovelStatus status) {
		this(status.getCode(), status.getLogMessage());
		this.status = status;
	}
	
	public ServiceException(NovelStatus status, Throwable e) {
		this(status.getCode(), status.getLogMessage(), e);
		this.status = status;
	}
	
	

	public ServiceException logArgs(Object...args) {
		this.logArgs = args;
		return this;
	}
	
	public ServiceException tipArgs(Object...args) {
		this.tipArgs = args;
		return this;
	}
	
	public ServiceException logAndTipArgs(Object...args) {
		this.logArgs = args;
		this.tipArgs = args;
		return this;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public NovelStatus getStatus() {
		return this.status;
	}
	
	@Override
	public String getMessage() {
		if (status != null) {
			return this.status.getLogMessage(logArgs);
		}
		return super.getMessage();
	}
	
	public String getTipMessage() {
		return this.status.getTipMessage(tipArgs);
	}
}
