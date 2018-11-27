package com.aswishes.wn.mvc.service.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class RequestMessage {

	private String requestId;

	private String eventName;

	private Object args;

	@JSONField(name = "rid")
	public String getRequestId() {
		return requestId;
	}

	@JSONField(name = "rid")
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@JSONField(name = "event")
	public String getEventName() {
		return eventName;
	}

	@JSONField(name = "event")
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Object getArgs() {
		return args;
	}

	public void setArgs(Object args) {
		this.args = args;
	}

}
