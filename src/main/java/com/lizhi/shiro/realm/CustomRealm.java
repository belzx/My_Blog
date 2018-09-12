package com.lizhi.shiro.realm;

import com.lizhi.bean.CacheUtils;
import com.lizhi.bean.User;
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
		// 从主体穿过来的信息中，获取用户名
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

	/**
	 * 获取角色
	 * 
	 * @param userName
	 * @return
	 */
	private Set<String> getRolesByUserName(String userName) {
		// 先从cache中获取
		User userByCache = getUserByCahe(userName);
		if (userByCache != null && userByCache.getRole() != null) {
			System.out.println("从cache中获取角色");
			return userByCache.getRole();
		}

		System.out.println("从数据库中获取获取角色");
		List<String> list = userDao.queryRolesByUserName(userName);
		Set<String> set = new HashSet(list);

		if (userByCache != null && userByCache.getRole() == null) {
			// 将获取的角色放到cache中
			userByCache.setRole(set);
			return set;
		} 
		return set;
	}

	/**
	 * 获取权限
	 * 
	 * @param userName
	 * @return
	 */
	private Set<String> getPermissionByUserName(String userName) {
		// 先从cache中获取
		User userByCache = getUserByCahe(userName);
		if (userByCache != null && userByCache.getPermission() != null) {
			System.out.println("从cache中获取权限");
			return userByCache.getPermission();
		}

		System.out.println("从数据库中获取获取权限");
		Set<String> roles = getRolesByUserName(userName);
		Set<String> set = new HashSet();
		for (String role : roles) {
			List<String> list = userDao.queryPermissionByUserName(role);
			set.addAll(list);
		}
		if (userByCache != null && userByCache.getPermission() == null) {
			// 将获取的权限放到cache中
			userByCache.setPermission(set);
			return set;
		}
		return set;

	}

	public String getPasswordByuserName(String userName) {
		// 先从cache中获取
		User userByCache = getUserByCahe(userName);
		if (userByCache != null) {
			System.out.println("从cache中获取密码");
			return userByCache.getPassword();
		}
		System.out.println("从dao中获取密码");
		User user = userDao.getPasswordByuserName(userName);
		if (user == null) {
			return null;
		}
		return user.getPassword();
	}

	public static void main(String[] args) {
		Md5Hash mark = new Md5Hash("123456", "Mark");
		System.out.println(mark.toString());
	}

	private User getUserByCahe(String userName) {
		// 先从cache中获取
		User userByCache = (User) CacheUtils.getObjectMap(new Md5Hash(userName).toString());
		return userByCache;
	}
}
