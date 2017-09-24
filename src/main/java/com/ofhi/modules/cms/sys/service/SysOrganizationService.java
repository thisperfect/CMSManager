package com.ofhi.modules.cms.sys.service;
import java.util.List;
import com.ofhi.modules.cms.sys.entity.pojo.SysOrganization;
public interface SysOrganizationService{
    long getSysOrganizationRowCount();
    List<SysOrganization> selectSysOrganization();
    SysOrganization selectSysOrganizationById(Long id);
    int insertSysOrganization(SysOrganization value);
    int insertNonEmptySysOrganization(SysOrganization value);
    int deleteSysOrganizationById(Long id);
    int updateSysOrganizationById(SysOrganization enti);
    int updateNonEmptySysOrganizationById(SysOrganization enti);
}