package com.lizhi.service.impl;

import javax.annotation.Resource;

import com.lizhi.dao.CustomMapper;
import org.springframework.stereotype.Service;

import com.lizhi.bean.User;
import com.lizhi.mapper.UserMapper;
import com.lizhi.service.IUserService;


@Service
public class UserServiceImpl implements IUserService {
	
	@Resource
	private UserMapper userMapper;


	@Override
	public CustomMapper<User, Integer> getMapper() {
		return userMapper;
	}
}
