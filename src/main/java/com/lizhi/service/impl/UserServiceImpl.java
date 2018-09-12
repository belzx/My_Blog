package com.lizhi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lizhi.bean.Message;
import com.lizhi.bean.User;
import com.lizhi.mybatis.mapper.MessageMapper;
import com.lizhi.mybatis.mapper.UserMapper;
import com.lizhi.service.IMessageService;
import com.lizhi.service.IUserService;


@Service
public class UserServiceImpl implements IUserService {
	
	@Resource
	private UserMapper userMapper;


	@Override
	public User getOne(String id) {
		// TODO Auto-generated method stub
		return userMapper.getOne(id);
	}


	@Override
	public User getOneByName(String username) {
		// TODO Auto-generated method stub
		return userMapper.getOneByName(username);
	}


	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		userMapper.insert(user);
	}

}
