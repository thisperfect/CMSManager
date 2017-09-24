package com.ofhi.modules.cms.sys.service;

import java.util.List;

import com.ofhi.common.exception.RequestErrorException;
import com.ofhi.modules.cms.sys.entity.pojo.SysUser;
import com.ofhi.modules.cms.sys.entity.vo.UserRegister;
import com.ofhi.common.Assist;

public interface SysUserService {

	SysUser selectSysUser(SysUser sysUser);

	List<SysUser> selectSysUser(Assist assist);

	SysUser selectSysUserById(Long id);

	int insertSysUser(SysUser value);

	int insertNonEmptySysUser(SysUser value);

	int deleteSysUserById(Long id);

	int deleteSysUser(Assist assist);

	int updateSysUserById(SysUser enti);

	int updateSysUser(SysUser value, Assist assist);

	int updateNonEmptySysUserById(SysUser enti);

	int updateNonEmptySysUser(SysUser value, Assist assist);

	/**
	 * 检查登入名是否存在，电子邮箱是否被绑定其它账户，存在或者绑定抛出异常
	 * @param username 登入账号
	 * @param email 电子邮件
	 * @throws RequestErrorException
	 */
	void checkUsernameAndEmailExists(String username, String email) throws RequestErrorException;

	/**
	 * 注册用户
	 * @param register
	 * @return
	 * @throws RequestErrorException
	 */
	SysUser insertOnRegisterUserAccount(UserRegister register) throws Exception;

	/**
	 * 激活账户
	 * @param email
	 * @throws RequestErrorException
	 */
	void activateAccount(String email) throws RequestErrorException;
}