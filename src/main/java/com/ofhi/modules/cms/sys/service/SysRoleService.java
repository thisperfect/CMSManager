package com.ofhi.modules.cms.sys.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ofhi.common.util.SearchHelper;
import com.ofhi.modules.cms.sys.entity.pojo.SysRole;
import com.ofhi.common.Assist;

public interface SysRoleService {
	long getSysRoleRowCount(Assist assist);

	PageInfo<SysRole> selectSysRole(SearchHelper<SysRole> searchHelper);

	SysRole selectSysRoleById(Long id);

	int insertSysRole(SysRole value);

	int insertNonEmptySysRole(SysRole value);

	int deleteSysRoleById(Long id);

	int deleteSysRole(Assist assist);

	int updateSysRoleById(SysRole enti);

	int updateSysRole(SysRole value, Assist assist);

	int updateNonEmptySysRoleById(SysRole enti);

	int updateNonEmptySysRole(SysRole value, Assist assist);
}