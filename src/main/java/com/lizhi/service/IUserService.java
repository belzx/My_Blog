package com.lizhi.service;

import java.util.List;

import com.lizhi.bean.Message;
import com.lizhi.bean.User;


public interface IUserService {

	User getOne(String id);
	
	User getOneByName(String username);
	
	void insertUser(User user);
}
