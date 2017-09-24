package com.ofhi.modules.cms.sys.service.impl;
import java.util.List;
import com.ofhi.modules.cms.sys.dao.SysOrganizationDao;
import com.ofhi.modules.cms.sys.entity.pojo.SysOrganization;
import com.ofhi.modules.cms.sys.service.SysOrganizationService;

public class SysOrganizationServiceImpl implements SysOrganizationService {
    private SysOrganizationDao sysOrganizationDao;
    @Override
    public long getSysOrganizationRowCount(){
        return sysOrganizationDao.getSysOrganizationRowCount();
    }
    @Override
    public List<SysOrganization> selectSysOrganization(){
        return sysOrganizationDao.selectSysOrganization();
    }
    @Override
    public SysOrganization selectSysOrganizationById(Long id){
        return sysOrganizationDao.selectSysOrganizationById(id);
    }
    @Override
    public int insertSysOrganization(SysOrganization value){
        return sysOrganizationDao.insertSysOrganization(value);
    }
    @Override
    public int insertNonEmptySysOrganization(SysOrganization value){
        return sysOrganizationDao.insertNonEmptySysOrganization(value);
    }
    @Override
    public int deleteSysOrganizationById(Long id){
        return sysOrganizationDao.deleteSysOrganizationById(id);
    }
    @Override
    public int updateSysOrganizationById(SysOrganization enti){
        return sysOrganizationDao.updateSysOrganizationById(enti);
    }
    @Override
    public int updateNonEmptySysOrganizationById(SysOrganization enti){
        return sysOrganizationDao.updateNonEmptySysOrganizationById(enti);
    }

    public SysOrganizationDao getSysOrganizationDao() {
        return this.sysOrganizationDao;
    }

    public void setSysOrganizationDao(SysOrganizationDao sysOrganizationDao) {
        this.sysOrganizationDao = sysOrganizationDao;
    }

}