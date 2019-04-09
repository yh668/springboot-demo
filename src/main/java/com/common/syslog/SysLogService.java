package com.common.syslog;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.SyslogMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Users Service实现
 * 
 * @author
 * @date 2018-12-12
 */
@Service
@Slf4j
@Transactional
public class SysLogService {

	private static final Logger logger = Logger.getLogger(SysLogService.class);

	/**
	 * Dao
	 */
	@Autowired
	private SyslogMapper syslogMapper;

	public boolean save(SysLogBO sysLogBO, String flag) {
//		// 这里就不做具体实现了
//		logger.info(sysLogBO.getParams());
//		log.info(sysLogBO.getParams());
//
//		String remart;
//		if (flag.equals("succeed")) {
//			remart = "用户XX执行了: " + sysLogBO.getRemark() + " 操作成功！";
//		} else {
//			remart = "用户XX执行了: " + sysLogBO.getRemark() + " 操作失败！";
//		}
//
//		sysLogBO.setRemark(remart);
//		syslogMapper.insert(sysLogBO);

		return true;
	}

}
