package com.ofhi.modules.cms.sys.controller;

import com.github.pagehelper.PageInfo;
import com.ofhi.common.base.BaseController;
import com.ofhi.common.util.SearchHelper;
import com.ofhi.modules.cms.sys.entity.pojo.Employees;
import com.ofhi.modules.cms.sys.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA
 * Created By laizuan
 * Date: 2017-9-26
 * Time: 20:25
 */
@Controller
@RequestMapping("/employees")
public class EmployeesController extends BaseController {

    @Autowired
    private EmployeesService employeesService;

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = produces)
    public String findEmployees(SearchHelper<Employees> searchHelper, Model model) {
        try {
            PageInfo<Employees> pageInfo = employeesService.selectEmployees(searchHelper);
            model.addAttribute("pageInfo", pageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "organized/employess_list";
    }

}
