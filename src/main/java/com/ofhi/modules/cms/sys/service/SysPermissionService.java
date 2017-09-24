package com.ofhi.modules.cms.sys.service;

import java.util.List;

import com.ofhi.modules.cms.sys.entity.pojo.SysPermission;
import com.ofhi.common.Assist;

public interface SysPermissionService {
	long getSysPermissionRowCount(Assist assist);

	List<SysPermission> selectSysPermission(Assist assist);

	SysPermission selectSysPermissionById(Long id);

	int insertSysPermission(SysPermission value);

	int insertNonEmptySysPermission(SysPermission value);

	int deleteSysPermissionById(Long id);

	int deleteSysPermission(Assist assist);

	int updateSysPermissionById(SysPermission enti);

	int updateSysPermission(SysPermission value, Assist assist);

	int updateNonEmptySysPermissionById(SysPermission enti);

	int updateNonEmptySysPermission(SysPermission value, Assist assist);
}