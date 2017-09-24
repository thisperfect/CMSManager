package com.ofhi.modules.cms.sys.service.impl;

import java.util.List;

import com.ofhi.modules.cms.sys.dao.SysIpInterceptDao;
import com.ofhi.modules.cms.sys.entity.pojo.SysIpIntercept;
import com.ofhi.modules.cms.sys.service.SysIpInterceptService;
import com.ofhi.common.Assist;

public class SysIpInterceptServiceImpl implements SysIpInterceptService {
	
	private SysIpInterceptDao sysIpInterceptDao;

	@Override
	public long getSysIpInterceptRowCount(Assist assist) {
		return sysIpInterceptDao.getSysIpInterceptRowCount(assist);
	}

	@Override
	public List<SysIpIntercept> selectSysIpIntercept(Assist assist) {
		return sysIpInterceptDao.selectSysIpIntercept(assist);
	}

	@Override
	public SysIpIntercept selectSysIpInterceptById(Long id) {
		return sysIpInterceptDao.selectSysIpInterceptById(id);
	}

	@Override
	public int insertSysIpIntercept(SysIpIntercept value) {
		return sysIpInterceptDao.insertSysIpIntercept(value);
	}

	@Override
	public int insertNonEmptySysIpIntercept(SysIpIntercept value) {
		return sysIpInterceptDao.insertNonEmptySysIpIntercept(value);
	}

	@Override
	public int deleteSysIpInterceptById(Long id) {
		return sysIpInterceptDao.deleteSysIpInterceptById(id);
	}

	@Override
	public int deleteSysIpIntercept(Assist assist) {
		return sysIpInterceptDao.deleteSysIpIntercept(assist);
	}

	@Override
	public int updateSysIpInterceptById(SysIpIntercept enti) {
		return sysIpInterceptDao.updateSysIpInterceptById(enti);
	}

	@Override
	public int updateSysIpIntercept(SysIpIntercept value, Assist assist) {
		return sysIpInterceptDao.updateSysIpIntercept(value, assist);
	}

	@Override
	public int updateNonEmptySysIpInterceptById(SysIpIntercept enti) {
		return sysIpInterceptDao.updateNonEmptySysIpInterceptById(enti);
	}

	@Override
	public int updateNonEmptySysIpIntercept(SysIpIntercept value, Assist assist) {
		return sysIpInterceptDao.updateNonEmptySysIpIntercept(value, assist);
	}

	public SysIpInterceptDao getSysIpInterceptDao() {
		return this.sysIpInterceptDao;
	}

	public void setSysIpInterceptDao(SysIpInterceptDao sysIpInterceptDao) {
		this.sysIpInterceptDao = sysIpInterceptDao;
	}

}