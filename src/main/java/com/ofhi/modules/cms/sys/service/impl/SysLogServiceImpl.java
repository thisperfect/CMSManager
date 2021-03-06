package com.ofhi.modules.cms.sys.service.impl;
import java.util.List;
import com.ofhi.modules.cms.sys.dao.SysLogDao;
import com.ofhi.modules.cms.sys.entity.pojo.SysLog;
import com.ofhi.modules.cms.sys.service.SysLogService;
public class SysLogServiceImpl implements SysLogService{
    private SysLogDao sysLogDao;
    @Override
    public long getSysLogRowCount(){
        return sysLogDao.getSysLogRowCount();
    }
    @Override
    public List<SysLog> selectSysLog(){
        return sysLogDao.selectSysLog();
    }
    @Override
    public SysLog selectSysLogById(Long id){
        return sysLogDao.selectSysLogById(id);
    }
    @Override
    public int insertSysLog(SysLog value){
        return sysLogDao.insertSysLog(value);
    }
    @Override
    public int insertNonEmptySysLog(SysLog value){
        return sysLogDao.insertNonEmptySysLog(value);
    }
    @Override
    public int deleteSysLogById(Long id){
        return sysLogDao.deleteSysLogById(id);
    }
    @Override
    public int updateSysLogById(SysLog enti){
        return sysLogDao.updateSysLogById(enti);
    }
    @Override
    public int updateNonEmptySysLogById(SysLog enti){
        return sysLogDao.updateNonEmptySysLogById(enti);
    }

    public SysLogDao getSysLogDao() {
        return this.sysLogDao;
    }

    public void setSysLogDao(SysLogDao sysLogDao) {
        this.sysLogDao = sysLogDao;
    }

}SysLogDao sysLogDao) {
        this.sysLogDao = sysLogDao;
    }

}