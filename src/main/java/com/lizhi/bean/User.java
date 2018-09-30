package com.lizhi.bean;

import java.io.Serializable;
import java.util.Set;

public class User implements Serializable,Cloneable{
	private static final long serialVersionUID = -5809782578272943999L;
	private Integer id;
	private String username;
    private String password;
    private boolean remenberMe;
    private String salt;
    
    private Set<String> role;
    private Set<String> permission;
    
    private String ico;
    
    
    
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
    
    public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}



    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isRemenberMe() {
        return remenberMe;
    }

    public void setRemenberMe(boolean remenberMe) {
        this.remenberMe = remenberMe;
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
				", remenberMe=" + remenberMe +
				", salt='" + salt + '\'' +
				", role=" + role +
				", permission=" + permission +
				", ico='" + ico + '\'' +
				'}';
	}
}
