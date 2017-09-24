package com.ofhi.modules.cms.sys.entity.pojo;

import lombok.Data;

@Data
public class SysUserRole implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String description;
    private Long parentId;
    private String name;
    private Integer isFinal;
    private Long rank;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private Long createBy;
    private Long updateBy;
}
