package com.ofhi.modules.cms.sys.dao;

import com.ofhi.modules.cms.sys.entity.pojo.SysIpIntercept;

import java.util.List;
import com.ofhi.common.Assist;
import org.apache.ibatis.annotations.Param;

public interface SysIpInterceptDao {
	long getSysIpInterceptRowCount(Assist assist);

	List<SysIpIntercept> selectSysIpIntercept(Assist assist);

	SysIpIntercept selectSysIpInterceptById(Long id);

	int insertSysIpIntercept(SysIpIntercept value);

	int insertNonEmptySysIpIntercept(SysIpIntercept value);

	int deleteSysIpInterceptById(Long id);

	int deleteSysIpIntercept(Assist assist);

	int updateSysIpInterceptById(SysIpIntercept enti);

	int updateSysIpIntercept(@Param("enti") SysIpIntercept value,
			@Param("assist") Assist assist);

	int updateNonEmptySysIpInterceptById(SysIpIntercept enti);

	int updateNonEmptySysIpIntercept(@Param("enti") SysIpIntercept value,
			@Param("assist") Assist assist);
}