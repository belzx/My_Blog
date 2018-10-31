package com.lizhi.mapper;

import java.util.List;

import com.lizhi.dao.CustomMapper;
import org.apache.ibatis.annotations.Mapper;

import com.lizhi.bean.User;
@Mapper
public interface UserMapper extends CustomMapper<User,Integer> {


//    @Select("SELECT * FROM users WHERE id = #{id}")
//    @Results({
//        @Result(property = "username",  column = "username"),
//        @Result(property = "password", column = "password")
//    })
//    User getOne(String id);
//
//
//    @Select("SELECT * FROM users WHERE username = #{username}")
//    @Results({
//    	@Result(property = "id",  column = "id"),
//        @Result(property = "username",  column = "username"),
//        @Result(property = "password", column = "password")
//    })
//    User getOneByName(String username);
//
//    @Insert("INSERT INTO users(username,password,password_salt) VALUES(#{username}, #{password}, #{salt})")
//    @Results({
//        @Result(property = "salt",  column = "password_salt")
//    })
//    void insert(User user);

}