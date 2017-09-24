package com.ofhi.modules.cms.sys.service.impl;

import java.util.List;

import com.ofhi.modules.cms.sys.service.SysDictionaryGroupService;
import com.ofhi.modules.cms.sys.dao.SysDictionaryGroupDao;
import com.ofhi.modules.cms.sys.entity.pojo.SysDictionaryGroup;
import com.ofhi.common.Assist;

public class SysDictionaryGroupServiceImpl implements SysDictionaryGroupService {
	
	
	private SysDictionaryGroupDao sysDictionaryGroupDao;

	@Override
	public long getSysDictionaryGroupRowCount(Assist assist) {
		return sysDictionaryGroupDao.getSysDictionaryGroupRowCount(assist);
	}

	@Override
	public List<SysDictionaryGroup> selectSysDictionaryGroup(Assist assist) {
		return sysDictionaryGroupDao.selectSysDictionaryGroup(assist);
	}

	@Override
	public SysDictionaryGroup selectSysDictionaryGroupById(Long id) {
		return sysDictionaryGroupDao.selectSysDictionaryGroupById(id);
	}

	@Override
	public int insertSysDictionaryGroup(SysDictionaryGroup value) {
		return sysDictionaryGroupDao.insertSysDictionaryGroup(value);
	}

	@Override
	public int insertNonEmptySysDictionaryGroup(SysDictionaryGroup value) {
		return sysDictionaryGroupDao.insertNonEmptySysDictionaryGroup(value);
	}

	@Override
	public int deleteSysDictionaryGroupById(Long id) {
		return sysDictionaryGroupDao.deleteSysDictionaryGroupById(id);
	}

	@Override
	public int deleteSysDictionaryGroup(Assist assist) {
		return sysDictionaryGroupDao.deleteSysDictionaryGroup(assist);
	}

	@Override
	public int updateSysDictionaryGroupById(SysDictionaryGroup enti) {
		return sysDictionaryGroupDao.updateSysDictionaryGroupById(enti);
	}

	@Override
	public int updateSysDictionaryGroup(SysDictionaryGroup value, Assist assist) {
		return sysDictionaryGroupDao.updateSysDictionaryGroup(value, assist);
	}

	@Override
	public int updateNonEmptySysDictionaryGroupById(SysDictionaryGroup enti) {
		return sysDictionaryGroupDao.updateNonEmptySysDictionaryGroupById(enti);
	}

	@Override
	public int updateNonEmptySysDictionaryGroup(SysDictionaryGroup value,
			Assist assist) {
		return sysDictionaryGroupDao.updateNonEmptySysDictionaryGroup(value,
				assist);
	}

	public SysDictionaryGroupDao getSysDictionaryGroupDao() {
		return this.sysDictionaryGroupDao;
	}

	public void setSysDictionaryGroupDao(
			SysDictionaryGroupDao sysDictionaryGroupDao) {
		this.sysDictionaryGroupDao = sysDictionaryGroupDao;
	}

}