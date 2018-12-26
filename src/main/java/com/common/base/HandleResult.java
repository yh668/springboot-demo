package com.common.base;


public class HandleResult<T> {

	private String status;

	private T data;

	private String msg;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public HandleResult() {
		this.status = "200";
	}

	public HandleResult(String code) {
		this.status = code;
	}

	public HandleResult(String code, T data) {
		this.status = code;
		this.data = data;
	}

	public HandleResult(String code, T data, String msg) {
		this.status = code;
		this.data = data;
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}