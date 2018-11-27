package com.aswishes.wn.mvc.service.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class ResponseMessage {

	private int code;

	private Object result;

	public ResponseMessage() {
	}

	public ResponseMessage(int code, String errorMsg) {
		this.code = code;
		this.result = errorMsg;
	}

	public ResponseMessage(int code, Object result) {
		this.code = code;
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@JSONField(name = "r")
	public Object getResult() {
		return result;
	}

	@JSONField(name = "r")
	public void setResult(Object result) {
		this.result = result;
	}

}
