package com.ofhi.modules.cms.sys.dao;
import com.ofhi.modules.cms.sys.entity.pojo.SysDictionaryGroup;

import java.util.List;
import com.ofhi.common.Assist;
import org.apache.ibatis.annotations.Param;

public interface SysDictionaryGroupDao {
    long getSysDictionaryGroupRowCount(Assist assist);
    List<SysDictionaryGroup> selectSysDictionaryGroup(Assist assist);
    SysDictionaryGroup selectSysDictionaryGroupById(Long id);
    int insertSysDictionaryGroup(SysDictionaryGroup value);
    int insertNonEmptySysDictionaryGroup(SysDictionaryGroup value);
    int deleteSysDictionaryGroupById(Long id);
    int deleteSysDictionaryGroup(Assist assist);
    int updateSysDictionaryGroupById(SysDictionaryGroup enti);
    int updateSysDictionaryGroup(@Param("enti") SysDictionaryGroup value, @Param("assist") Assist assist);
    int updateNonEmptySysDictionaryGroupById(SysDictionaryGroup enti);
    int updateNonEmptySysDictionaryGroup(@Param("enti") SysDictionaryGroup value, @Param("assist") Assist assist);
}