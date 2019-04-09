
package com.demo.dao;

import org.springframework.stereotype.Repository;

import com.common.oplog.LogEntity;

/**
 * Syslog Dao
 * 
 * @author
 * @date 2019-04-02
 */
@Repository
public interface LogMapper {

	void addLogInfo(LogEntity logEntity);

}
