package com.ofhi.modules.cms.sys.service;
import java.util.List;

import com.ofhi.modules.cms.sys.entity.pojo.SysIpIntercept;
import com.ofhi.common.Assist;
public interface SysIpInterceptService{
    long getSysIpInterceptRowCount(Assist assist);
    List<SysIpIntercept> selectSysIpIntercept(Assist assist);
    SysIpIntercept selectSysIpInterceptById(Long id);
    int insertSysIpIntercept(SysIpIntercept value);
    int insertNonEmptySysIpIntercept(SysIpIntercept value);
    int deleteSysIpInterceptById(Long id);
    int deleteSysIpIntercept(Assist assist);
    int updateSysIpInterceptById(SysIpIntercept enti);
    int updateSysIpIntercept(SysIpIntercept value, Assist assist);
    int updateNonEmptySysIpInterceptById(SysIpIntercept enti);
    int updateNonEmptySysIpIntercept(SysIpIntercept value, Assist assist);
}