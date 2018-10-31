package com.lizhi.mapper;


import com.lizhi.bean.BlogCommont;
import com.lizhi.dao.CustomMapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BlogCommontMapper extends CustomMapper<BlogCommont,String> {
}
