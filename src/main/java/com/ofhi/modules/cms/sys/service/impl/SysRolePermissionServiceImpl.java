package com.ofhi.modules.cms.sys.service.impl;
import java.util.List;
import com.ofhi.modules.cms.sys.dao.SysRolePermissionDao;
import com.ofhi.modules.cms.sys.entity.pojo.SysRolePermission;
import com.ofhi.common.Assist;
import com.ofhi.modules.cms.sys.service.SysRolePermissionService;
public class SysRolePermissionServiceImpl implements SysRolePermissionService{
    private SysRolePermissionDao sysRolePermissionDao;
    @Override
    public long getSysRolePermissionRowCount(Assist assist){
        return sysRolePermissionDao.getSysRolePermissionRowCount(assist);
    }
    @Override
    public List<SysRolePermission> selectSysRolePermission(Assist assist){
        return sysRolePermissionDao.selectSysRolePermission(assist);
    }
    @Override
    public SysRolePermission selectSysRolePermissionById(Long id){
        return sysRolePermissionDao.selectSysRolePermissionById(id);
    }
    @Override
    public int insertSysRolePermission(SysRolePermission value){
        return sysRolePermissionDao.insertSysRolePermission(value);
    }
    @Override
    public int insertNonEmptySysRolePermission(SysRolePermission value){
        return sysRolePermissionDao.insertNonEmptySysRolePermission(value);
    }
    @Override
    public int deleteSysRolePermissionById(Long id){
        return sysRolePermissionDao.deleteSysRolePermissionById(id);
    }
    @Override
    public int deleteSysRolePermission(Assist assist){
        return sysRolePermissionDao.deleteSysRolePermission(assist);
    }
    @Override
    public int updateSysRolePermissionById(SysRolePermission enti){
        return sysRolePermissionDao.updateSysRolePermissionById(enti);
    }
    @Override
    public int updateSysRolePermission(SysRolePermission value, Assist assist){
        return sysRolePermissionDao.updateSysRolePermission(value,assist);
    }
    @Override
    public int updateNonEmptySysRolePermissionById(SysRolePermission enti){
        return sysRolePermissionDao.updateNonEmptySysRolePermissionById(enti);
    }
    @Override
    public int updateNonEmptySysRolePermission(SysRolePermission value, Assist assist){
        return sysRolePermissionDao.updateNonEmptySysRolePermission(value,assist);
    }

    public SysRolePermissionDao getSysRolePermissionDao() {
        return this.sysRolePermissionDao;
    }

    public void setSysRolePermissionDao(SysRolePermissionDao sysRolePermissionDao) {
        this.sysRolePermissionDao = sysRolePermissionDao;
    }

}