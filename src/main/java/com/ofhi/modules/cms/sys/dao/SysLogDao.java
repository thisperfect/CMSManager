package com.ofhi.modules.cms.sys.dao;

import com.ofhi.modules.cms.sys.entity.pojo.SysLog;

import java.util.List;
public interface SysLogDao{
    long getSysLogRowCount();

    List<SysLog> selectSysLog();

    SysLog selectSysLogById(Integer id);

    int insertSysLog(SysLog value);

    int insertNonEmptySysLog(SysLog value);

    int deleteSysLogById(Integer id);

    int updateSysLogById(SysLog enti);

    int updateNonEmptySysLogById(SysLog enti);
}