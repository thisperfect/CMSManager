package com.ofhi.modules.cms.sys.service;
import java.util.List;
import com.ofhi.modules.cms.sys.entity.pojo.SysDictionaryGroup;
import com.ofhi.common.Assist;
public interface SysDictionaryGroupService{
    long getSysDictionaryGroupRowCount(Assist assist);
    List<SysDictionaryGroup> selectSysDictionaryGroup(Assist assist);
    SysDictionaryGroup selectSysDictionaryGroupById(Long id);
    int insertSysDictionaryGroup(SysDictionaryGroup value);
    int insertNonEmptySysDictionaryGroup(SysDictionaryGroup value);
    int deleteSysDictionaryGroupById(Long id);
    int deleteSysDictionaryGroup(Assist assist);
    int updateSysDictionaryGroupById(SysDictionaryGroup enti);
    int updateSysDictionaryGroup(SysDictionaryGroup value, Assist assist);
    int updateNonEmptySysDictionaryGroupById(SysDictionaryGroup enti);
    int updateNonEmptySysDictionaryGroup(SysDictionaryGroup value, Assist assist);
}