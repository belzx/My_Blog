package com.lizhi.service;

import java.util.List;

import com.lizhi.bean.Message;


public interface IMessageService {

	List<Message> list();

	int count();

}
