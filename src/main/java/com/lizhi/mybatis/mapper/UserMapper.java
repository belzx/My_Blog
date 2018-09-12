package com.lizhi.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lizhi.bean.User;
@Mapper
public interface UserMapper {

//    @Select("SELECT * FROM users")
//    @Results({
//        @Result(property = "userSex",  column = "user_sex", javaType = User.class),
//        @Result(property = "nickName", column = "nick_name")
//    })
//    List<User> getAll();
//
    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
        @Result(property = "username",  column = "username"),
        @Result(property = "password", column = "password")
    })
    User getOne(String id);
    
    
    @Select("SELECT * FROM users WHERE username = #{username}")
    @Results({
    	@Result(property = "id",  column = "id"),
        @Result(property = "username",  column = "username"),
        @Result(property = "password", column = "password")
    })
    User getOneByName(String username);

    @Insert("INSERT INTO users(username,password,password_salt) VALUES(#{username}, #{password}, #{salt})")
    @Results({
        @Result(property = "salt",  column = "password_salt")
    })
    void insert(User user);
//
//    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
//    void update(User user);
//
//    @Delete("DELETE FROM users WHERE id =#{id}")
//    void delete(Long id);

}