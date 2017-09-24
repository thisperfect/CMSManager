package com.ofhi.common.security.shiro;

import com.ofhi.common.Constant;
import com.ofhi.common.Assist;
import com.ofhi.common.user.UserActive;
import com.ofhi.common.user.UserConst;
import com.ofhi.modules.cms.sys.entity.bind.UserBind;
import com.ofhi.modules.cms.sys.entity.pojo.SysUser;
import com.ofhi.modules.cms.sys.service.SysPermissionService;
import com.ofhi.modules.cms.sys.service.SysRoleService;
import com.ofhi.modules.cms.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class shiroRealm extends AuthorizingRealm {

	private org.slf4j.Logger log = LoggerFactory.getLogger(shiroRealm.class);

	@Autowired 
	private SysUserService sysUserService;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysPermissionService sysPermissionService;
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	/**
	 * 权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		log.debug("===> shiro 开始权限认证");
		// 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
		// (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			SecurityUtils.getSubject().logout();
			return null;
		}
		UserActive loginUser = (UserActive)principalCollection.getPrimaryPrincipal();

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
        return info;  
	}

	/**
	 * 登录认证;
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
		log.debug("===> shiro 开始登入验证");
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		List<SysUser> users = sysUserService.selectSysUser(new Assist(Assist.and_eq("login_name",token.getUsername())));
		
		if (CollectionUtils.isEmpty(users)) {
			throw new UnknownAccountException();
		}
		SysUser user = users.get(0);
		if (user == null || user.getStatus() == UserConst.ACCOUNT_STATUS_DEL) {//没有找到该用户
			throw new UnknownAccountException();
		}
		if (user.getStatus() == UserConst.ACCOUNT_STATUS_DISABLED) {//账户被禁用
			throw new DisabledAccountException();
		}
		if (user.getStatus() == UserConst.ACCOUNT_STATUS_ACTIVATE) {
			throw new LockedAccountException();
		}
		/*UserActive userActive = UserBind.toActive(user);*/
		return new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(),ByteSource.Util.bytes(UserConst.PASSWORD_SALT), getName());
	}
	
	@Override
	protected void doClearCache(PrincipalCollection principals) {
		String key = Constant.shiro_cache_prefix + principals.getPrimaryPrincipal().toString();
		log.debug("===> doClearCache：{}",key);
		redisTemplate.delete(key);
	}

	@Override
	protected void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		log.debug("===> clearCachedAuthorizationInfo");
	}
	
}
