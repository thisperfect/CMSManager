package com.ofhi.modules.cms.sys.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Created By laiz
 * Date: 2017\8\1 0001
 * Time: 13:01
 */
@Getter
@Setter
public class TreeMenu {
    // 菜单id
    private Integer id;
    // 菜单名称
    private String text;
    // 父菜单id
    private String parentId;
    // 菜单url
    private String url;
    // 菜单图标
    private String icon;
    // 菜单顺序
    private int order;
    //节点状态对象
    private MenuState state;
    // 子菜单
    private List<TreeMenu> children;
}
