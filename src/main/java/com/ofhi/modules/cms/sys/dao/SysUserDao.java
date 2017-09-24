package com.ofhi.modules.cms.sys.dao;

import com.ofhi.common.Assist;
import com.ofhi.modules.cms.sys.entity.pojo.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SysUserDao extends Mapper<SysUser> {
    long getSysUserRowCount(Assist assist);

    List<SysUser> selectSysUser(Assist assist);

    SysUser selectSysUserById(Long id);

    int insertSysUser(SysUser value);

    int insertNonEmptySysUser(SysUser value);

    int deleteSysUserById(Long id);

    int deleteSysUser(Assist assist);

    int updateSysUserById(SysUser enti);

    int updateSysUser(@Param("enti") SysUser value, @Param("assist") Assist assist);

    int updateNonEmptySysUserById(SysUser enti);

    int updateNonEmptySysUser(@Param("enti") SysUser value, @Param("assist") Assist assist);
}