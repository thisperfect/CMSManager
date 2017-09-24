package com.ofhi.modules.cms.sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ofhi.common.Assist;
import com.ofhi.common.util.SearchHelper;
import com.ofhi.modules.cms.sys.dao.SysRoleDao;
import com.ofhi.modules.cms.sys.entity.pojo.SysRole;
import com.ofhi.modules.cms.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;

	@Override
	public long getSysRoleRowCount(Assist assist) {
		return sysRoleDao.getSysRoleRowCount(assist);
	}

	@Override
	public PageInfo<SysRole> selectSysRole(SearchHelper<SysRole> searchHelper) {
		PageHelper.startPage(searchHelper.getPageNum(), searchHelper.getPageSize());
		List<SysRole> sysRoles = sysRoleDao.select(searchHelper.getT());
		PageInfo<SysRole> pageInfo = new PageInfo(sysRoles);
		return pageInfo;
	}

	@Override
	public SysRole selectSysRoleById(Long id) {
		return sysRoleDao.selectSysRoleById(id);
	}

	@Override
	public int insertSysRole(SysRole value) {
		return sysRoleDao.insertSysRole(value);
	}

	@Override
	public int insertNonEmptySysRole(SysRole value) {
		return sysRoleDao.insertNonEmptySysRole(value);
	}

	@Override
	public int deleteSysRoleById(Long id) {
		return sysRoleDao.deleteSysRoleById(id);
	}

	@Override
	public int deleteSysRole(Assist assist) {
		return sysRoleDao.deleteSysRole(assist);
	}

	@Override
	public int updateSysRoleById(SysRole enti) {
		return sysRoleDao.updateSysRoleById(enti);
	}

	@Override
	public int updateSysRole(SysRole value, Assist assist) {
		return sysRoleDao.updateSysRole(value, assist);
	}

	@Override
	public int updateNonEmptySysRoleById(SysRole enti) {
		return sysRoleDao.updateNonEmptySysRoleById(enti);
	}

	@Override
	public int updateNonEmptySysRole(SysRole value, Assist assist) {
		return sysRoleDao.updateNonEmptySysRole(value, assist);
	}

	public SysRoleDao getSysRoleDao() {
		return this.sysRoleDao;
	}

	public void setSysRoleDao(SysRoleDao sysRoleDao) {
		this.sysRoleDao = sysRoleDao;
	}

}