package com.ofhi.modules.cms.sys.entity.pojo;

import lombok.Data;

@Data
public class SysPermission {
    private Long id;
    private String name;
    private String description;
    private String code;
    private String icon;
    private String url;
    private Long parentId;
    private Integer isFinal;
    private Long rank;//排序
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private Long createBy;
    private Long updateBy;
}
