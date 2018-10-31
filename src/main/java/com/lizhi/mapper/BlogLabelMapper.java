package com.lizhi.mapper;


import com.lizhi.bean.BlogLabel;
import com.lizhi.dao.CustomMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 */
@Mapper
public interface BlogLabelMapper extends CustomMapper<BlogLabel,String> {
}
