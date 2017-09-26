package com.ofhi.modules.cms.sys.service;


import com.github.pagehelper.PageInfo;
import com.ofhi.common.Assist;
import com.ofhi.common.util.SearchHelper;
import com.ofhi.modules.cms.sys.entity.pojo.Employees;

public interface EmployeesService {
    long getEmployeesRowCount(Assist assist);

    PageInfo<Employees> selectEmployees(SearchHelper<Employees> searchHelper);

    Employees selectEmployeesById(Integer id);

    int insertEmployees(Employees value);

    int insertNonEmptyEmployees(Employees value);

    int deleteEmployeesById(Integer id);

    int deleteEmployees(Assist assist);

    int updateEmployeesById(Employees enti);

    int updateEmployees(Employees value, Assist assist);

    int updateNonEmptyEmployeesById(Employees enti);

    int updateNonEmptyEmployees(Employees value, Assist assist);
}