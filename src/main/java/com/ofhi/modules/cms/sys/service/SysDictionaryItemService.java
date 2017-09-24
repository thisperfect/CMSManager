package com.ofhi.modules.cms.sys.service;
import java.util.List;

import com.ofhi.modules.cms.sys.entity.pojo.SysDictionaryItem;
import com.ofhi.common.Assist;
public interface SysDictionaryItemService{
    long getSysDictionaryItemRowCount(Assist assist);
    List<SysDictionaryItem> selectSysDictionaryItem(Assist assist);
    SysDictionaryItem selectSysDictionaryItemById(Long id);
    int insertSysDictionaryItem(SysDictionaryItem value);
    int insertNonEmptySysDictionaryItem(SysDictionaryItem value);
    int deleteSysDictionaryItemById(Long id);
    int deleteSysDictionaryItem(Assist assist);
    int updateSysDictionaryItemById(SysDictionaryItem enti);
    int updateSysDictionaryItem(SysDictionaryItem value, Assist assist);
    int updateNonEmptySysDictionaryItemById(SysDictionaryItem enti);
    int updateNonEmptySysDictionaryItem(SysDictionaryItem value, Assist assist);
}