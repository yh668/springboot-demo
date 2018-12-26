package com.common.base;


import java.util.Map;

public class ServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1608316879739850559L;
	protected String code;
	protected String message;
	protected Map<String, String> codeMsgList;

	public ServiceException() {
		super();
	}

	public ServiceException(String code) {
		this.code = code;
	}

	public ServiceException(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public ServiceException(Map<String, String> codemsgs) {
		codeMsgList = codemsgs;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getCodeMsgList() {
		return codeMsgList;
	}

	public void setCodeMsgList(Map<String, String> codeMsgList) {
		this.codeMsgList = codeMsgList;
	}
}