package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.UsersMapper;
import com.demo.model.Users;
import com.demo.service.UsersService;

/**
 * Users Service实现
 * 
 * @author
 * @date 2018-12-12
 */
@Service
@Transactional
public class UsersServiceImpl implements UsersService {

	
	@Autowired
	private UsersMapper usersMapper;
	
	@Override
	public List<Users> findAll() {
		return usersMapper.findAll();
	}

	@Override
	public Users findById(int id) {
		return usersMapper.findById(id);
	}

}
