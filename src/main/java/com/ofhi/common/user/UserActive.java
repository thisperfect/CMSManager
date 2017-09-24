package com.ofhi.common.user;

import com.google.common.collect.Lists;
import com.ofhi.modules.cms.sys.entity.vo.TreeMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By laiz
 * Date: 2017\9\18 0018
 * Time: 17:24
 */
@Getter
@Setter
public class UserActive {
    private Long id;
    private String loginName;
    private String name;
    private String email;
    private String phone;
    private String organizationId;
    private String path;
    private boolean admin = false;
    private List<TreeMenu> userMenu = Lists.newArrayList();
}
