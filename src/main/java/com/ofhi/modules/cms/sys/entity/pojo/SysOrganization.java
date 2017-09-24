package com.ofhi.modules.cms.sys.entity.pojo;


import lombok.Data;

@Data
public class SysOrganization implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String icon;
    private Long parentId;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private String createBy;
    private String updateBy;
    private Integer isFinal;
}
