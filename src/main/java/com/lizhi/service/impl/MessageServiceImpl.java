package com.lizhi.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lizhi.bean.Message;
import com.lizhi.mybatis.mapper.MessageMapper;
import com.lizhi.service.IMessageService;


@Service
public class MessageServiceImpl implements IMessageService {
	
	@Resource
	private MessageMapper messageMapper;

	@Override
	public List<Message> list() {
		return messageMapper.list();
	}

	@Override
	public int count() {
		return messageMapper.count();
	}

}
