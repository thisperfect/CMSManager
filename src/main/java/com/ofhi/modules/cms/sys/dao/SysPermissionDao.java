package com.ofhi.modules.cms.sys.dao;
import com.ofhi.modules.cms.sys.entity.pojo.SysPermission;
import java.util.List;
import com.ofhi.common.Assist;
import org.apache.ibatis.annotations.Param;
public interface SysPermissionDao{
    long getSysPermissionRowCount(Assist assist);
    List<SysPermission> selectSysPermission(Assist assist);
    SysPermission selectSysPermissionById(Long id);
    int insertSysPermission(SysPermission value);
    int insertNonEmptySysPermission(SysPermission value);
    int deleteSysPermissionById(Long id);
    int deleteSysPermission(Assist assist);
    int updateSysPermissionById(SysPermission enti);
    int updateSysPermission(@Param("enti") SysPermission value, @Param("assist") Assist assist);
    int updateNonEmptySysPermissionById(SysPermission enti);
    int updateNonEmptySysPermission(@Param("enti") SysPermission value, @Param("assist") Assist assist);
}