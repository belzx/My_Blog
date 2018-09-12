package com.lizhi.mybatis.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.lizhi.bean.Message;
import com.lizhi.mybatis.config.util.MyMapper;

/**
 * @author zhangyd
 * @version V1.0
 * @Description
 * @date 2017年3月10日 下午2:42:45
 * @modify
 * @Review
 * @since JDK ： 1.7
 */
@Mapper
public interface MessageMapper extends MyMapper<Message> {
    List<Message> list();

    int count();
}
