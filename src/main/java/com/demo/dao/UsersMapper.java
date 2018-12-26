
package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.Users;

/**
 * Users Dao
 * 
 * @author
 * @date 2018-12-12
 */
@Repository
public interface UsersMapper {

	List<Users> findAll();

	Users findById(@Param("id") int id);

}
