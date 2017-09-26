package com.ofhi.modules.cms.sys.dao;


import com.ofhi.common.Assist;
import com.ofhi.modules.cms.sys.entity.pojo.Employees;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface EmployeesDao extends Mapper<Employees> {
    long getEmployeesRowCount(Assist assist);
    List<Employees> selectEmployees(Assist assist);
    Employees selectEmployeesById(Integer id);
    int insertEmployees(Employees value);
    int insertNonEmptyEmployees(Employees value);
    int deleteEmployeesById(Integer id);
    int deleteEmployees(Assist assist);
    int updateEmployeesById(Employees enti);
    int updateEmployees(@Param("enti") Employees value, @Param("assist") Assist assist);
    int updateNonEmptyEmployeesById(Employees enti);
    int updateNonEmptyEmployees(@Param("enti") Employees value, @Param("assist") Assist assist);
}