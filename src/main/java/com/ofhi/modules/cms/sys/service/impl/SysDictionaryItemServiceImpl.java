package com.ofhi.modules.cms.sys.service.impl;
import java.util.List;

import com.ofhi.modules.cms.sys.dao.SysDictionaryItemDao;
import com.ofhi.modules.cms.sys.entity.pojo.SysDictionaryItem;
import com.ofhi.common.Assist;
import com.ofhi.modules.cms.sys.service.SysDictionaryItemService;
public class SysDictionaryItemServiceImpl implements SysDictionaryItemService{
    private SysDictionaryItemDao sysDictionaryItemDao;
    @Override
    public long getSysDictionaryItemRowCount(Assist assist){
        return sysDictionaryItemDao.getSysDictionaryItemRowCount(assist);
    }
    @Override
    public List<SysDictionaryItem> selectSysDictionaryItem(Assist assist){
        return sysDictionaryItemDao.selectSysDictionaryItem(assist);
    }
    @Override
    public SysDictionaryItem selectSysDictionaryItemById(Long id){
        return sysDictionaryItemDao.selectSysDictionaryItemById(id);
    }
    @Override
    public int insertSysDictionaryItem(SysDictionaryItem value){
        return sysDictionaryItemDao.insertSysDictionaryItem(value);
    }
    @Override
    public int insertNonEmptySysDictionaryItem(SysDictionaryItem value){
        return sysDictionaryItemDao.insertNonEmptySysDictionaryItem(value);
    }
    @Override
    public int deleteSysDictionaryItemById(Long id){
        return sysDictionaryItemDao.deleteSysDictionaryItemById(id);
    }
    @Override
    public int deleteSysDictionaryItem(Assist assist){
        return sysDictionaryItemDao.deleteSysDictionaryItem(assist);
    }
    @Override
    public int updateSysDictionaryItemById(SysDictionaryItem enti){
        return sysDictionaryItemDao.updateSysDictionaryItemById(enti);
    }
    @Override
    public int updateSysDictionaryItem(SysDictionaryItem value, Assist assist){
        return sysDictionaryItemDao.updateSysDictionaryItem(value,assist);
    }
    @Override
    public int updateNonEmptySysDictionaryItemById(SysDictionaryItem enti){
        return sysDictionaryItemDao.updateNonEmptySysDictionaryItemById(enti);
    }
    @Override
    public int updateNonEmptySysDictionaryItem(SysDictionaryItem value, Assist assist){
        return sysDictionaryItemDao.updateNonEmptySysDictionaryItem(value,assist);
    }

    public SysDictionaryItemDao getSysDictionaryItemDao() {
        return this.sysDictionaryItemDao;
    }

    public void setSysDictionaryItemDao(SysDictionaryItemDao sysDictionaryItemDao) {
        this.sysDictionaryItemDao = sysDictionaryItemDao;
    }

}