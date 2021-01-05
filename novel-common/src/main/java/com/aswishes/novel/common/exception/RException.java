package com.aswishes.novel.common.exception;

public class RException extends RuntimeException {
	private static final long serialVersionUID = -7702677952748817159L;

	public RException(Throwable e) {
		super(e);
	}
	
	public RException(String msg) {
		super(msg);
	}
}
