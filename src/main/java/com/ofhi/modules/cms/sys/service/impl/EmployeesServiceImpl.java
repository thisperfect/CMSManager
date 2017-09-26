package com.ofhi.modules.cms.sys.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ofhi.common.Assist;
import com.ofhi.common.util.SearchHelper;
import com.ofhi.modules.cms.sys.dao.EmployeesDao;
import com.ofhi.modules.cms.sys.entity.pojo.Employees;
import com.ofhi.modules.cms.sys.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeesServiceImpl implements EmployeesService {

    @Autowired
    private EmployeesDao employeesDao;

    @Override
    public long getEmployeesRowCount(Assist assist){
        return employeesDao.getEmployeesRowCount(assist);
    }
    @Override
    public  PageInfo<Employees>  selectEmployees(SearchHelper<Employees> searchHelper){
        PageHelper.startPage(searchHelper.getPageNum(), searchHelper.getPageSize());
        List<Employees> employeess = employeesDao.select(searchHelper.getT());
        PageInfo<Employees> pageInfo = new PageInfo(employeess);
        return pageInfo;
    }
    @Override
    public Employees selectEmployeesById(Integer id){
        return employeesDao.selectEmployeesById(id);
    }
    @Override
    public int insertEmployees(Employees value){
        return employeesDao.insertEmployees(value);
    }
    @Override
    public int insertNonEmptyEmployees(Employees value){
        return employeesDao.insertNonEmptyEmployees(value);
    }
    @Override
    public int deleteEmployeesById(Integer id){
        return employeesDao.deleteEmployeesById(id);
    }
    @Override
    public int deleteEmployees(Assist assist){
        return employeesDao.deleteEmployees(assist);
    }
    @Override
    public int updateEmployeesById(Employees enti){
        return employeesDao.updateEmployeesById(enti);
    }
    @Override
    public int updateEmployees(Employees value, Assist assist){
        return employeesDao.updateEmployees(value,assist);
    }
    @Override
    public int updateNonEmptyEmployeesById(Employees enti){
        return employeesDao.updateNonEmptyEmployeesById(enti);
    }
    @Override
    public int updateNonEmptyEmployees(Employees value, Assist assist){
        return employeesDao.updateNonEmptyEmployees(value,assist);
    }

    public EmployeesDao getEmployeesDao() {
        return this.employeesDao;
    }

    public void setEmployeesDao(EmployeesDao employeesDao) {
        this.employeesDao = employeesDao;
    }

}