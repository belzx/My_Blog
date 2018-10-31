package com.lizhi.service.impl;

import com.lizhi.bean.BlogCommont;
import com.lizhi.dao.CustomMapper;
import com.lizhi.mapper.BlogCommontMapper;
import com.lizhi.service.IBlogCommontService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BlogCommontServiceImpl implements IBlogCommontService {
    @Resource
    private BlogCommontMapper commontMapper;

    @Override
    public CustomMapper<BlogCommont, String> getMapper() {
        return commontMapper;
    }
}
