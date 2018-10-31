package com.lizhi.shiro.realm.dao;

import com.lizhi.bean.User;
import com.lizhi.service.RedisService;
import com.lizhi.shiro.realm.CustomRealm;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

@Component
public class UserDaoImpl implements com.lizhi.shiro.realm.dao.UserDao {

    private static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    public static final String USER_KEY = "1234567890123:%s";

    @Resource
    private RedisService redisService;

    @Resource
    private JdbcTemplate jdbcTemplate;

    public User getPasswordByuserName(String userName) {
        String sql = "SELECT id,username,password from users WHERE username = ? ";

        List<User> list = jdbcTemplate.query(sql, new String[]{userName}, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    public List<String> queryRolesByUserName(String userName) {
        String sql = "SELECT role_name from user_roles WHERE username = ? ";

        return jdbcTemplate.query(sql, new String[]{userName}, new RowMapper<String>() {
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("role_name");
            }
        });
    }

    public List<String> queryPermissionByUserName(String Role) {
        String sql = "SELECT permission from roles_permissions WHERE role_name = ? ";

        return jdbcTemplate.query(sql, new String[]{Role}, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("permission");
            }
        });
    }

    @Override
    public User getUser(String userName) {
        User user;
        try {
            user = (User) redisService.get(USER_KEY + userName);
            if (user != null) {
                if (user.getId() == null) {
                    redisService.remove(USER_KEY + userName);
                    user = getPasswordByuserName(userName);
//                    log.info("从dao中获取user");
                }else{
//                    log.info("从redis中获取user");
                }
            } else {
//                log.info("从dao中获取user");
                user = getPasswordByuserName(userName);
            }
            if (user == null) {
                log.warn("没有角色");
                return null;
            }
            user.setPermission(new HashSet<String>(queryPermissionByUserName(userName)));
            user.setRole(new HashSet<String>(queryRolesByUserName(userName)));
            redisService.set(USER_KEY + userName, user);
            log.error("getuser :{}", user.toString());
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void clearCaheUser(String userName) {
         redisService.remove(USER_KEY + userName);
    }
}
