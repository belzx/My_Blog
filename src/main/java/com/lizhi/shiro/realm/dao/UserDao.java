package com.lizhi.shiro.realm.dao;

import com.lizhi.bean.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    User getPasswordByuserName(String userName);
    List<String> queryRolesByUserName(String userName);
    List<String> queryPermissionByUserName(String Role);
    User getUser(String userName);
}
