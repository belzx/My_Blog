package com.lizhi.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class User extends CustomEntity<String> {
    private static final long serialVersionUID = -5809782578272943999L;

    private String username;

    private String password;

    private Date createTime;

    private Date lastLoginTime;

    private boolean rememberMe;

    private Integer status = 1;

    private Set<String> role;

    private Set<String> permission;

    private String ico;

    private int isLogin = 1; //0注销 1登录

    public int getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(int isLogin) {
        this.isLogin = isLogin;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public Set<String> getPermission() {
        return permission;
    }

    public void setPermission(Set<String> permission) {
        this.permission = permission;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean remenberMe) {
        this.rememberMe = remenberMe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", RememberMe=" + rememberMe +
                ", role=" + role +
                ", permission=" + permission +
                ", ico='" + ico + '\'' +
                '}';
    }
}
