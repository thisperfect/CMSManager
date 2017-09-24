CREATE TABLE `sys_dictionary_item` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`sys_data_group_id`  bigint(20) NULL DEFAULT NULL COMMENT 'sys_dictionary_group表主键id' ,
`key_value`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值' ,
`key_name`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称' ,
`is_final`  int(11) NULL DEFAULT 1 COMMENT '是否可删除' ,
`rank`  bigint(20) NULL DEFAULT 0 COMMENT '排序' ,
`create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
`create_by`  bigint(20) NULL DEFAULT 0 COMMENT '创建人' ,
`update_by`  bigint(20) NULL DEFAULT 0 COMMENT '更热人' ,
`status`  tinyint(4) NULL DEFAULT 1 COMMENT '数据状态,1:正常,2:删除,3:禁用账号' ,
`description`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=6
ROW_FORMAT=DYNAMIC
;
