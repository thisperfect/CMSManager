CREATE TABLE `sys_user_role` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`sys_user_id`  bigint(20) NULL DEFAULT NULL ,
`sys_role_id`  bigint(20) NULL DEFAULT NULL ,
`create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
`update_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
`create_by`  bigint(20) NULL DEFAULT 0 COMMENT '创建人id' ,
`update_by`  bigint(20) NULL DEFAULT 0 COMMENT '更新人id' ,
`is_final`  tinyint(4) NULL DEFAULT NULL COMMENT '是否能修改' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=9
ROW_FORMAT=DYNAMIC
;

