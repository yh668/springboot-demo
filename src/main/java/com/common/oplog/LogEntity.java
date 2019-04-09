package com.common.oplog;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LogEntity implements Serializable {

	/**
	 * 序列化版本号
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uuid;
	private String userid;
	private String username;
	private String ip;
	private String status;
	private String operatetype;
	private String detail;
	private String operatetime;
	private String operatecontent;
	private String subsystemid;

}
