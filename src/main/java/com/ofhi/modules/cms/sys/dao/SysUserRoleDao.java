package com.ofhi.modules.cms.sys.dao;
import com.ofhi.modules.cms.sys.entity.pojo.SysUserRole;

import java.util.List;
import com.ofhi.common.Assist;
import org.apache.ibatis.annotations.Param;
public interface SysUserRoleDao{
    long getSysUserRoleRowCount(Assist assist);
    List<SysUserRole> selectSysUserRole(Assist assist);
    SysUserRole selectSysUserRoleById(Long id);
    int insertSysUserRole(SysUserRole value);
    int insertNonEmptySysUserRole(SysUserRole value);
    int deleteSysUserRoleById(Long id);
    int deleteSysUserRole(Assist assist);
    int updateSysUserRoleById(SysUserRole enti);
    int updateSysUserRole(@Param("enti") SysUserRole value, @Param("assist") Assist assist);
    int updateNonEmptySysUserRoleById(SysUserRole enti);
    int updateNonEmptySysUserRole(@Param("enti") SysUserRole value, @Param("assist") Assist assist);
}