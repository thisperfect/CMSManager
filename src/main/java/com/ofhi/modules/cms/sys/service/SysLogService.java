package com.ofhi.modules.cms.sys.service;
import java.util.List;
import com.ofhi.modules.cms.sys.entity.pojo.SysLog;
public interface SysLogService{
    long getSysLogRowCount();
    List<SysLog> selectSysLog();
    SysLog selectSysLogById(Long id);
    int insertSysLog(SysLog value);
    int insertNonEmptySysLog(SysLog value);
    int deleteSysLogById(Long id);
    int updateSysLogById(SysLog enti);
    int updateNonEmptySysLogById(SysLog enti);
}i);
}