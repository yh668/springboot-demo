package com.demo.service;

import java.util.List;

import com.demo.model.Users;

/**
 * Users Service
 * 
 * @author
 * @date 2018-12-12
 */
public interface UsersService {

	List<Users> findAll();

	Users findById(int id);

}
