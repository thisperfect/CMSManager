CREATE TABLE `sys_permission` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`name`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称' ,
`description`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述' ,
`code`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限代码' ,
`icon`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标' ,
`url`  varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转路径' ,
`parent_id`  bigint(20) NULL DEFAULT NULL COMMENT '父级id' ,
`is_final`  int(11) NULL DEFAULT 1 COMMENT '是否可删除' ,
`rank`  bigint(20) NULL DEFAULT 0 COMMENT '排序' ,
`create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
`create_by`  bigint(20) NULL DEFAULT 0 COMMENT '创建人id' ,
`update_by`  bigint(20) NULL DEFAULT 0 COMMENT '更新人id' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=48
ROW_FORMAT=DYNAMIC
;

