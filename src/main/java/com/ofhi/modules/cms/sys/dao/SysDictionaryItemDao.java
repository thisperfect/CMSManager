package com.ofhi.modules.cms.sys.dao;

import com.ofhi.modules.cms.sys.entity.pojo.SysDictionaryItem;

import java.util.List;
import com.ofhi.common.Assist;
import org.apache.ibatis.annotations.Param;

public interface SysDictionaryItemDao {
	long getSysDictionaryItemRowCount(Assist assist);

	List<SysDictionaryItem> selectSysDictionaryItem(Assist assist);

	SysDictionaryItem selectSysDictionaryItemById(Long id);

	int insertSysDictionaryItem(SysDictionaryItem value);

	int insertNonEmptySysDictionaryItem(SysDictionaryItem value);

	int deleteSysDictionaryItemById(Long id);

	int deleteSysDictionaryItem(Assist assist);

	int updateSysDictionaryItemById(SysDictionaryItem enti);

	int updateSysDictionaryItem(@Param("enti") SysDictionaryItem value,
			@Param("assist") Assist assist);

	int updateNonEmptySysDictionaryItemById(SysDictionaryItem enti);

	int updateNonEmptySysDictionaryItem(@Param("enti") SysDictionaryItem value,
			@Param("assist") Assist assist);
}
