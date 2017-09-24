package com.ofhi.modules.cms.sys.entity.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA
 * Created By laiz
 * Date: 2017\8\1 0001
 * Time: 15:22
 */
@Setter
@Getter
public class MenuState {

    private boolean selected ;//节点处于被选中状态
    private boolean opened ;//节点处于打开状态
    private boolean disabled ;//节点不可选
    private boolean checked ;//用于checkbox插件 - 勾选该checkbox(只有当 tie_selection 处于 false时有效)

    public MenuState(){}
    public MenuState(boolean opened){
        this.opened = opened;
    }

}
