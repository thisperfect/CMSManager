package com.ofhi.modules.cms.sys.dao;
import com.ofhi.modules.cms.sys.entity.pojo.SysRole;
import java.util.List;
import com.ofhi.common.Assist;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface SysRoleDao extends Mapper<SysRole> {
    long getSysRoleRowCount(Assist assist);
    List<SysRole> selectSysRole(Assist assist);
    SysRole selectSysRoleById(Long id);
    int insertSysRole(SysRole value);
    int insertNonEmptySysRole(SysRole value);
    int deleteSysRoleById(Long id);
    int deleteSysRole(Assist assist);
    int updateSysRoleById(SysRole enti);
    int updateSysRole(@Param("enti") SysRole value, @Param("assist") Assist assist);
    int updateNonEmptySysRoleById(SysRole enti);
    int updateNonEmptySysRole(@Param("enti") SysRole value, @Param("assist") Assist assist);
}