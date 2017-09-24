package com.ofhi.modules.cms.sys.controller;

import com.github.pagehelper.PageInfo;
import com.ofhi.common.Assist;
import com.ofhi.common.base.BaseController;
import com.ofhi.common.exception.RequestErrorException;
import com.ofhi.common.util.SearchHelper;
import com.ofhi.modules.cms.sys.entity.pojo.SysRole;
import com.ofhi.modules.cms.sys.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By laiz
 * Date: 2017\9\18 0018
 * Time: 17:46
 */
@Controller
@RequestMapping("sys/role")
public class SysRoleController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("/list")
    public String list(SearchHelper<SysRole> searchHelper, Model model) {
        try {
            PageInfo<SysRole> sysRolePageInfo = sysRoleService.selectSysRole(searchHelper);
            model.addAttribute("roles", sysRolePageInfo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RequestErrorException(e.getMessage());
        }
        return "sysRole/list";
    }

}
