package com.lizhi.service.impl;

import com.lizhi.bean.BlogLabel;
import com.lizhi.dao.CustomMapper;
import com.lizhi.mapper.BlogLabelMapper;
import com.lizhi.service.IBlogLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class BlogLabelServiceImpl implements IBlogLabelService {

	@Resource
	private BlogLabelMapper blogMapper;

	@Override
	public CustomMapper<BlogLabel, String> getMapper() {
		return blogMapper;
	}
}