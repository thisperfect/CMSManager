package com.ofhi.modules.cms.sys.service.impl;
import java.util.List;

import com.ofhi.modules.cms.sys.entity.pojo.SysUserRole;
import com.ofhi.modules.cms.sys.service.SysUserRoleService;
import com.ofhi.modules.cms.sys.dao.SysUserRoleDao;
import com.ofhi.common.Assist;

public class SysUserRoleServiceImpl implements SysUserRoleService {
    private SysUserRoleDao sysUserRoleDao;
    @Override
    public long getSysUserRoleRowCount(Assist assist){
        return sysUserRoleDao.getSysUserRoleRowCount(assist);
    }
    @Override
    public List<SysUserRole> selectSysUserRole(Assist assist){
        return sysUserRoleDao.selectSysUserRole(assist);
    }
    @Override
    public SysUserRole selectSysUserRoleById(Long id){
        return sysUserRoleDao.selectSysUserRoleById(id);
    }
    @Override
    public int insertSysUserRole(SysUserRole value){
        return sysUserRoleDao.insertSysUserRole(value);
    }
    @Override
    public int insertNonEmptySysUserRole(SysUserRole value){
        return sysUserRoleDao.insertNonEmptySysUserRole(value);
    }
    @Override
    public int deleteSysUserRoleById(Long id){
        return sysUserRoleDao.deleteSysUserRoleById(id);
    }
    @Override
    public int deleteSysUserRole(Assist assist){
        return sysUserRoleDao.deleteSysUserRole(assist);
    }
    @Override
    public int updateSysUserRoleById(SysUserRole enti){
        return sysUserRoleDao.updateSysUserRoleById(enti);
    }
    @Override
    public int updateSysUserRole(SysUserRole value, Assist assist){
        return sysUserRoleDao.updateSysUserRole(value,assist);
    }
    @Override
    public int updateNonEmptySysUserRoleById(SysUserRole enti){
        return sysUserRoleDao.updateNonEmptySysUserRoleById(enti);
    }
    @Override
    public int updateNonEmptySysUserRole(SysUserRole value, Assist assist){
        return sysUserRoleDao.updateNonEmptySysUserRole(value,assist);
    }

    public SysUserRoleDao getSysUserRoleDao() {
        return this.sysUserRoleDao;
    }

    public void setSysUserRoleDao(SysUserRoleDao sysUserRoleDao) {
        this.sysUserRoleDao = sysUserRoleDao;
    }

}