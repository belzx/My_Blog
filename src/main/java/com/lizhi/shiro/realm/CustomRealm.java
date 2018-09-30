package com.lizhi.shiro.realm;

import com.lizhi.bean.CacheUtils;
import com.lizhi.bean.User;
import com.lizhi.service.RedisService;
import com.lizhi.shiro.realm.dao.UserDao;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 利用cache 管理session 以及user
 * @author lx
 *
 */
public class CustomRealm extends AuthorizingRealm {

	private static Logger log = LoggerFactory.getLogger(CustomRealm.class);

	@Resource
	private UserDao userDao;
	{
		super.setName("customRealm");
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		String userName = (String) principalCollection.getPrimaryPrincipal();
		Set<String> roles = getRolesByUserName(userName);
		Set<String> permission = getPermissionByUserName(userName);

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.setRoles(roles);
		simpleAuthorizationInfo.setStringPermissions(permission);

		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		// 从主体传过来的信息中，获取用户名
		String userName = (String) authenticationToken.getPrincipal();

		// 通过用户名到数据库中获取凭证
		String passwd = getPasswordByuserName(userName);
		if (passwd == null) {
			return null;
		}
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, passwd,
				"customRealm");
		simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userName));
		return simpleAuthenticationInfo;
	}


	private Set<String> getRolesByUserName(String userName) {
		User user = userDao.getUser(userName);
		return  user == null ? null: user.getRole();
	}

	private Set<String> getPermissionByUserName(String userName) {
		User user = userDao.getUser(userName);
		return  user == null ? null: user.getPermission();
	}

	public String getPasswordByuserName(String userName) {
		User user = userDao.getUser(userName);
		return  user == null? null:user.getPassword();
	}



}
