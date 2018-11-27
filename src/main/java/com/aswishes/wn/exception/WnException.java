package com.aswishes.wn.exception;

public class WnException extends RuntimeException {

	private static final long serialVersionUID = 8360854775754288748L;
	
	private int code;

	public WnException() {
	}
	
	public WnException(int code, String msg) {
		super(msg);
		this.code = code;
	}
	
	public WnException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public WnException(int code, String msg, Throwable cause) {
		super(msg, cause);
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
}
