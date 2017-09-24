package com.ofhi.modules.cms.sys.dao;
import com.ofhi.modules.cms.sys.entity.pojo.SysRolePermission;

import java.util.List;
import com.ofhi.common.Assist;
import org.apache.ibatis.annotations.Param;
public interface SysRolePermissionDao{
    long getSysRolePermissionRowCount(Assist assist);
    List<SysRolePermission> selectSysRolePermission(Assist assist);
    SysRolePermission selectSysRolePermissionById(Long id);
    int insertSysRolePermission(SysRolePermission value);
    int insertNonEmptySysRolePermission(SysRolePermission value);
    int deleteSysRolePermissionById(Long id);
    int deleteSysRolePermission(Assist assist);
    int updateSysRolePermissionById(SysRolePermission enti);
    int updateSysRolePermission(@Param("enti") SysRolePermission value, @Param("assist") Assist assist);
    int updateNonEmptySysRolePermissionById(SysRolePermission enti);
    int updateNonEmptySysRolePermission(@Param("enti") SysRolePermission value, @Param("assist") Assist assist);
}