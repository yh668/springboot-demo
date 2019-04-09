package com.common.syslog;

import java.io.Serializable;

public class SysLogBO implements Serializable {

	/**
	 * 序列化版本号
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String className;

	private String methodName;

	private String params;

	private Long exeuTime;

	private String remark;

	private String createDate;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Long getExeuTime() {
		return exeuTime;
	}

	public void setExeuTime(Long exeuTime) {
		this.exeuTime = exeuTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
