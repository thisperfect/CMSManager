package com.ofhi.modules.cms.sys.entity.pojo;

import lombok.Data;

@Data
public class SysDictionaryItem {
    private Long id;
    private Long sysDataGroupId;
    private String keyValue;
    private String keyName;
    private Integer isFinal;
    private Long rank;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private Long createBy;
    private Long updateBy;
    private Integer status;
    private String description;
}
