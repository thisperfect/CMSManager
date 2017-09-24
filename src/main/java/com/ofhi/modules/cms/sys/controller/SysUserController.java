package com.ofhi.modules.cms.sys.controller;

import com.ofhi.common.base.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA
 * Created By laiz
 * Date: 2017\7\28 0028
 * Time: 17:34
 */
@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {

    @RequestMapping(value = "index")
    public String indexPage(Model model) {
        logger.debug("====> indexPage");
        return "sys/index";
    }

    @RequestMapping(value = "list")
    public String userList() {

        return "sysUser/user_list";
    }


    @RequestMapping(value = "loginOut")
    public String loginOut() {
        Subject currentUser = SecurityUtils.getSubject();
        if(currentUser != null)
            currentUser.logout();
        return "redirect:../system/login.shtml";
    }
}
