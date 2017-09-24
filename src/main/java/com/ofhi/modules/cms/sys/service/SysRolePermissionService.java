package com.ofhi.modules.cms.sys.service;
import java.util.List;

import com.ofhi.modules.cms.sys.entity.pojo.SysRolePermission;
import com.ofhi.common.Assist;
public interface SysRolePermissionService{
    long getSysRolePermissionRowCount(Assist assist);
    List<SysRolePermission> selectSysRolePermission(Assist assist);
    SysRolePermission selectSysRolePermissionById(Long id);
    int insertSysRolePermission(SysRolePermission value);
    int insertNonEmptySysRolePermission(SysRolePermission value);
    int deleteSysRolePermissionById(Long id);
    int deleteSysRolePermission(Assist assist);
    int updateSysRolePermissionById(SysRolePermission enti);
    int updateSysRolePermission(SysRolePermission value, Assist assist);
    int updateNonEmptySysRolePermissionById(SysRolePermission enti);
    int updateNonEmptySysRolePermission(SysRolePermission value, Assist assist);
}