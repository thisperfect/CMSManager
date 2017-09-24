package com.ofhi.modules.cms.sys.service;
import java.util.List;
import com.ofhi.modules.cms.sys.entity.pojo.SysUserRole;
import com.ofhi.common.Assist;
public interface SysUserRoleService{
    long getSysUserRoleRowCount(Assist assist);
    List<SysUserRole> selectSysUserRole(Assist assist);
    SysUserRole selectSysUserRoleById(Long id);
    int insertSysUserRole(SysUserRole value);
    int insertNonEmptySysUserRole(SysUserRole value);
    int deleteSysUserRoleById(Long id);
    int deleteSysUserRole(Assist assist);
    int updateSysUserRoleById(SysUserRole enti);
    int updateSysUserRole(SysUserRole value, Assist assist);
    int updateNonEmptySysUserRoleById(SysUserRole enti);
    int updateNonEmptySysUserRole(SysUserRole value, Assist assist);
}