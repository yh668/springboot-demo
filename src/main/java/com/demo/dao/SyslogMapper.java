
package com.demo.dao;

import org.springframework.stereotype.Repository;

import com.common.syslog.SysLogBO;

/**
 * Syslog Dao
 * 
 * @author
 * @date 2019-04-02
 */
@Repository
public interface SyslogMapper {

	int insert(SysLogBO syslog);

}
