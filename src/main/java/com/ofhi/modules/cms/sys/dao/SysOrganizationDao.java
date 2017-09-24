package com.ofhi.modules.cms.sys.dao;

import com.ofhi.modules.cms.sys.entity.pojo.SysOrganization;

import java.util.List;

public interface SysOrganizationDao {
    long getSysOrganizationRowCount();

    List<SysOrganization> selectSysOrganization();

    SysOrganization selectSysOrganizationById(Long id);

    int insertSysOrganization(SysOrganization value);

    int insertNonEmptySysOrganization(SysOrganization value);

    int deleteSysOrganizationById(Long id);

    int updateSysOrganizationById(SysOrganization enti);

    int updateNonEmptySysOrganizationById(SysOrganization enti);
}